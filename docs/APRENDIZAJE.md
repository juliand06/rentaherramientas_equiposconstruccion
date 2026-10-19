# APRENDIZAJE.md — Guía de estudio por fase

Por cada fase: conceptos clave aplicados, 3 preguntas de repaso tipo sustentación con respuesta, y errores comunes de junior que se evitaron.

---

## Fase 0 — Plan y convenciones

### Conceptos clave
- **Arquitectura hexagonal (puertos y adaptadores):** El dominio define interfaces (puertos); la infraestructura las implementa (adaptadores). El negocio no conoce la tecnología.
- **Regla de dependencias:** $\text{infrastructure} \to \text{application} \to \text{domain}$.
- **ADR (Architectural Decision Record):** Documentar por qué se tomó una decisión, las alternativas consideradas y sus consecuencias, no solo qué se hizo.

### Preguntas de repaso
1. **¿Por qué `application` no usa anotaciones de Spring?**
   Para que los casos de uso no dependan del framework. Se prueban con fakes en memoria y se podría cambiar Spring sin tocar el negocio. Los beans se declaran a mano en `BeansConfig`.
2. **¿Qué es un puerto y qué es un adaptador?**
   El puerto es una interfaz que pertenece al dominio o a la aplicación (ej. `ToolRepository`). El adaptador la implementa con una tecnología concreta (ej. JPA).
3. **¿Por qué se documentan las desviaciones respecto a los repos del profesor?**
   Porque un cambio sin explicación parece un error. Con el motivo escrito se puede defender en la sustentación.

### Errores comunes de junior evitados
- Poner lógica de negocio en el controlador.
- Dejar contraseñas en el YAML.
- Subir `target/` o `.env` al repositorio.

---

## Fase 1 — Estructura Maven multi-módulo y Docker Compose

### Conceptos clave
- **Maven multi-módulo con BOM:** El `pom.xml` padre centraliza versiones y gestiona dependencias mediante `dependencyManagement` y el BOM de Spring Boot, garantizando consistencia binaria sin fijar versiones manuales en submódulos.
- **Aislamiento de capas con ArchUnit:** Regla inviolable `infrastructure → application → domain`. Ningún módulo de negocio importa frameworks de infraestructura; ArchUnit lo verifica automáticamente en compilación.
- **Docker Compose con perfiles:** Separación de servicios (BD PostgreSQL 16, Backend Spring Boot 4 y Frontend Nginx) con variables de entorno y healthchecks.

### Preguntas de repaso
1. **¿Qué ventaja ofrece usar un BOM (Bill of Materials) en el pom padre?**
   Permite importar un conjunto de dependencias testeadas y compatibles entre sí (versiones transitivas) sin especificar la etiqueta `<version>` en cada módulo hijo, evitando conflictos de librerías.
2. **¿Cómo garantiza ArchUnit que el dominio se mantenga puro?**
   Analiza el bytecode de las clases compiladas y falla el build si alguna clase del paquete `domain` o `application` importa paquetes externos prohibidos como `org.springframework.*`, `jakarta.persistence.*` o `com.fasterxml.jackson.*`.
3. **¿Por qué la variable `SPRING_PROFILES_ACTIVE` controla el arranque?**
   Porque desacopla la configuración de desarrollo (`dev` con logs detallados e importación de `.env`) de la configuración de producción (`prod` con credenciales estrictas inyectadas por el entorno de contenedores).

### Errores comunes de junior evitados
- Usar versiones diferentes de librerías en distintos módulos.
- Hardcodear credenciales de base de datos en `application.yml`.
- Poner anotaciones `@Entity` o `@Service` dentro de `domain` o `application`.

---

## Fase 2 — Esquema, migraciones Flyway y archivos drawSQL

### Conceptos clave
- **Cuarta Forma Normal (4FN):** Eliminación de dependencias multivaluadas independientes. Por ejemplo, desacoplar teléfonos y direcciones de los usuarios en tablas separadas (`user_phones` y `user_addresses`), o desacoplar especificaciones técnicas e imágenes de las herramientas (`tool_specifications` y `tool_images`).
- **Control de disponibilidad a nivel de motor (`EXCLUDE USING gist`):** En lugar de confiar únicamente en validaciones por software susceptibles a condiciones de carrera (*race conditions*), PostgreSQL bloquea automáticamente reservas solapadas mediante la extensión `btree_gist` con rangos de fechas (`daterange`) y filtro de estado activo (`WHERE is_active = TRUE`).
- **Migraciones versionadas con Flyway y `ddl-auto: validate`:** La base de datos evoluciona mediante scripts reproducibles (`V1__schema.sql`, `V2__seed_data.sql`), mientras que Hibernate únicamente valida que las entidades Java coincidan con el esquema real, evitando alteraciones destructivas automáticas en producción.

### Preguntas de repaso
1. **¿Por qué se usa `EXCLUDE USING gist` en lugar de una simple consulta `SELECT ... WHERE dates OVERLAPS`?**
   Porque dos transacciones concurrentes pueden consultar la disponibilidad al mismo milisegundo, ver el equipo libre y ambas confirmar la reserva (*race condition*). El constraint `EXCLUDE` en PostgreSQL bloquea el solapamiento a nivel de motor de almacenamiento con índices GiST, garantizando aislamiento estricto.
2. **¿Qué problema de diseño resuelve separar `tools`, `tool_specifications` y `tool_images` en 4FN?**
   Si una herramienta tuviera múltiples fotos y múltiples especificaciones en la misma tabla o como colecciones no normalizadas, se produciría un producto cartesiano (anomalía multivaluada) donde agregar una foto obligaría a duplicar todas las especificaciones o viceversa. La 4FN elimina esta redundancia.
3. **¿Por qué los passwords en el seed `V2` se guardan como hashes BCrypt y no en texto plano?**
   Por seguridad y realismo: Spring Security utiliza `PasswordEncoder` con BCrypt por defecto. Almacenar hashes reales permite probar el inicio de sesión real desde los endpoints de autenticación desde el primer día sin requerir inicializadores ad-hoc.

### Errores comunes de junior evitados
- Usar `ddl-auto: update` o `create-drop` en lugar de migraciones versionadas con Flyway.
- Usar tipos `FLOAT` o `DOUBLE` para precios y depósitos en lugar de `NUMERIC(12, 2)` / `BigDecimal`.
- Olvidar índices en claves foráneas, lo que causa *table scans* lentos en operaciones `JOIN`.
- Insertar datos de prueba sin referencialidad completa o con menos de 20 registros por entidad.

---

## Fase 3 — Dominio puro y Modelado DDD

### Conceptos clave
- **Agregados y Raíces de Agregado (`AggregateRoot`):** Entidades que actúan como límites transaccionales de coherencia. El acceso a los objetos internos del agregado siempre pasa a través de la raíz.
- **Value Objects inmutables (`Money`, `DateRange`, `Email`):** Objetos definidos por sus atributos y no por una identidad numérica mutable. Utilizan Java *records* y validan sus reglas de negocio en el constructor compacto.
- **Eventos de Dominio (`ReservationConfirmed`):** Notificaciones inmutables de hechos relevantes ocurridos en el dominio, registrados en el agregado y despachados de forma desacoplada tras la persistencia.

### Preguntas de repaso
1. **¿Cuál es la diferencia sustancial entre una Entidad y un Value Object en DDD?**
   Una entidad posee una identidad única y persistente en el tiempo (un ID), mientras que un Value Object se define únicamente por el valor de sus campos (dos `Money` con $50.000 COP son intercambiables y equivalentes) y es completamente inmutable.
2. **¿Por qué no se debe usar `LocalDateTime.now()` dentro del código de dominio?**
   Porque rompe el determinismo de las pruebas unitarias y acopla el negocio al reloj del sistema físico. Se debe recibir la fecha por parámetro o a través de un puerto de abstracción de tiempo (`Clock` / `TimeProvider`).
3. **¿Cómo garantiza un agregado que sus invariantes nunca se rompan?**
   Mediante constructores privados y métodos factoría estáticos (`register`, `restore`). Las propiedades no tienen *setters* públicos; cualquier transición de estado se realiza a través de métodos de intención semántica (`confirm()`, `cancel()`) que evalúan las precondiciones antes de mutar el estado.

### Errores comunes de junior evitados
- Crear modelos "anémicos" (clases con solo getters y setters sin validaciones).
- Usar primitivos (`double`, `String`) para conceptos clave del dominio (obsesión por primitivos).
- Depender de librerías como Lombok o Jackson dentro del paquete de dominio.

---

## Fase 4 — Casos de uso y Persistencia (Rebanada Vertical)

### Conceptos clave
- **Rebanada Vertical (Vertical Slicing):** Implementación de una funcionalidad de punta a punta (controlador REST $\to$ caso de uso $\to$ dominio $\to$ adaptador JPA $\to$ base de datos) antes de replicar el patrón al resto del sistema.
- **Mapeo explícito (Mappers manuales):** Separación tajante entre la entidad de dominio y la entidad JPA (`Tool` vs `ToolJpaEntity`), evitando que las anotaciones de Hibernate contaminen la lógica de negocio.
- **Fakes en memoria para pruebas de aplicación:** Implementaciones ligeras de los puertos de repositorio (`InMemoryToolRepository`) que permiten probar los casos de uso a la velocidad de la memoria RAM sin necesidad de levantar bases de datos ni mocks frágiles.

### Preguntas de repaso
1. **¿Por qué se prefiere un mapper manual en lugar de anotar la clase de dominio directamente con `@Entity`?**
   Porque mezclar `@Entity` con el dominio acopla el modelo conceptual a las limitaciones técnicas de JPA/Hibernate (requerir constructor sin argumentos, colecciones no inmutables, campos proxy y mutabilidad requerida por el ORM).
2. **¿Por qué se recomienda probar los casos de uso con *fakes* en lugar de `@MockBean`?**
   Porque los fakes implementan el contrato real del puerto en memoria con estructuras de datos simples (`HashMap`), preservando el comportamiento de estado de forma determinista y ejecutando en milisegundos sin sobrecarga del contexto de Spring.
3. **¿Cómo se configuran los casos de uso en el contenedor IoC sin usar `@Service`?**
   En clases de configuración técnica ubicadas en infraestructura (`CatalogBeansConfig`), donde cada caso de uso se instancia como un `@Bean` inyectando sus puertos requeridos mediante constructores.

### Errores comunes de junior evitados
- Inyectar el repositorio JPA directamente en el controlador REST.
- Hacer transacciones largas que abarquen llamadas HTTP externas o conversiones de UI.
- Devolver entidades JPA directamente en las respuestas REST (fuga de abstracción).

---

## Fase 5 — Seguridad JWT y Control de Acceso

### Conceptos clave
- **Arquitectura de autenticación Stateless:** El servidor no mantiene sesiones en memoria (`SessionCreationPolicy.STATELESS`); cada petición HTTP contiene la identidad y los roles del usuario en el encabezado `Authorization: Bearer <token>`.
- **Rotación de tokens (Access y Refresh Tokens):** El Access Token tiene un tiempo de vida corto (15 minutos) para mitigar el impacto de su interceptación; el Refresh Token (7 días) permite obtener un nuevo Access Token sin requerir reingresar credenciales.
- **Control granular con `@EnableMethodSecurity`:** Autorización basada en roles (`ADMIN`, `PROVIDER`, `CLIENT`) aplicada tanto en el filtro de seguridad HTTP como a nivel de métodos de controlador.

### Preguntas de repaso
1. **¿Qué componentes integran un token JWT y cómo garantiza su integridad el servidor?**
   Está compuesto por tres partes separadas por puntos: Header (algoritmo), Payload (claims y datos del usuario) y Signature (firma HMAC-SHA256). La firma se genera combinando header + payload con una clave secreta privada que solo conoce el servidor; si algún dato es alterado, la firma no coincide y el token es rechazado.
2. **¿Por qué se desacoplan `UserDetailsService` y `JwtAuthenticationFilter`?**
   `UserDetailsService` solo se consulta en el momento del inicio de sesión (`/api/auth/login`) para verificar la contraseña contra la base de datos con BCrypt. En las peticiones subsiguientes, el filtro JWT autentica al usuario directamente parseando los claims del token sin consultar la base de datos en cada request, garantizando alto rendimiento.
3. **¿Por qué el secreto JWT no debe tener un valor por defecto en producción?**
   Porque un secreto por defecto hardcodeado en el código fuente permitiría a un atacante generar tokens arbitrarios con rol de Administrador y falsificar identidades libremente.

### Errores comunes de junior evitados
- Guardar contraseñas en texto plano o con algoritmos obsoletos (MD5, SHA-1).
- Poner datos sensibles (como contraseñas o datos financieros) dentro del payload del JWT.
- Configurar CORS con comodín `*` permitiendo el envío de credenciales (`allowCredentials=true`).

---

## Fase 6 — API REST, Manejo Global de Excepciones y Swagger

### Conceptos clave
- **Manejador Global de Excepciones (`@RestControllerAdvice`):** Centraliza la captura de excepciones técnicas y de negocio en un único componente, traduciéndolas a códigos HTTP apropiados (400, 401, 403, 404, 409, 500) y estructuras JSON consistentes (`ApiErrorResponse`).
- **Validación declarativa con Bean Validation (Jakarta Validation):** Uso de anotaciones en los DTOs de entrada (`@NotNull`, `@NotBlank`, `@DecimalMin`, `@Email`) con captura de errores campo por campo en `MethodArgumentNotValidException`.
- **Documentación viva con OpenAPI 3 / springdoc:** Documentación autogenerada y sincronizada con el código fuente accesible mediante Swagger UI, con soporte de autenticación Bearer interactiva.

### Preguntas de repaso
1. **¿Qué ventaja proporciona `ApiErrorResponse` frente a los errores estándar de Spring Boot?**
   Proporciona un contrato uniforme y predecible para el cliente web o móvil (`timestamp`, `status`, `error`, `message`, `path`), ocultando trazas internas de la base de datos o del framework que podrían comprometer la seguridad.
2. **¿Cuándo se debe responder con código HTTP 409 Conflict en lugar de 400 Bad Request?**
   El 409 se utiliza específicamente cuando la petición es sintácticamente correcta pero entra en conflicto con el estado actual del recurso en el servidor, como la duplicidad de un correo único o un código de maquinaria ya existente.
3. **¿Por qué los DTOs deben ser `records` en Java 21?**
   Porque los `records` son inmutables por diseño, proveen implementaciones automáticas de `equals()`, `hashCode()` y `toString()`, y eliminan la necesidad de código repetitivo de getters y constructores.

### Errores comunes de junior evitados
- Retornar código HTTP 200 OK cuando ocurre un error de negocio con un mensaje en el cuerpo JSON.
- Exponer el *stack trace* completo de la excepción de base de datos al cliente.
- Olvidar validar los identificadores UUID o recibir cadenas vacías en campos obligatorios.

---

## Fase 7 — Facturación, Generador PDF y Métricas BI

### Conceptos clave
- **Pasarela de pagos simulada y desacoplada:** El procesamiento de pagos opera detrás del puerto `PaymentRepository` y emite comprobantes de transacción inmutables vinculados a la reserva.
- **Generación de documentos PDF desacoplada:** La creación de facturas o comprobantes de alquiler se abstrae tras la interfaz `PdfGeneratorPort`, permitiendo cambiar el motor de renderizado sin afectar los casos de uso.
- **Métricas de Inteligencia de Negocios (BI):** Puerto `ReportingPort` y caso de uso `GetSystemMetricsUseCase` que consolidan el inventario, disponibilidad de maquinaria e ingresos brutos acumulados en pesos colombianos.

### Preguntas de repaso
1. **¿Por qué la generación de PDFs debe ser un puerto de aplicación y no estar dentro del caso de uso?**
   Porque construir un archivo binario PDF es un detalle técnico de salida (I/O). Si el día de mañana se cambia la librería de PDF o se genera una factura electrónica fiscal con un proveedor externo (DIAN), solo se reemplaza el adaptador sin tocar la lógica de facturación.
2. **¿Cómo se asegura la consistencia de inventario al completar un pago?**
   Al procesar el pago exitoso, la reserva transiciona a confirmada mediante eventos de dominio que actualizan la disponibilidad de la máquina asociada, impidiendo que sea rentada en fechas concurrentes.
3. **¿Qué beneficios ofrece reportar métricas precalculadas o agrupadas desde adaptadores dedicados?**
   Evita cargar miles de entidades en memoria mediante Hibernate para contar registros; el adaptador de reporting ejecuta consultas optimizadas de agregación a nivel de motor SQL.

### Errores comunes de junior evitados
- Bloquear hilos de procesamiento web esperando llamadas lentas de generación de documentos.
- Almacenar comprobantes con cálculos de moneda en punto flotante (`float`/`double`).
- Calcular métricas de negocio trayendo todos los datos a listas en memoria en lugar de usar funciones de agregación en la base de datos.

---

## Fase 8 — Frontend Industrial Interactivo

### Conceptos clave
- **Single Page Application (SPA) en JavaScript Vanilla:** Cero dependencias pesadas de compilación en el frontend; arquitectura basada en ES Modules nativos, manipulación del DOM y consumo de la API REST mediante `fetch` asíncrono con interceptores de token JWT.
- **Artefactos interactivos con HTML5 Canvas y Web Audio API:** Renderizado a 60 fotogramas por segundo de cinemática mecánica y sintetizador de sonido industrial procedimental para enriquecer la experiencia de usuario.
- **Control de estado por rol en el cliente:** Paneles dinámicos que adaptan la interfaz gráfica según el rol activo (`CLIENT`, `PROVIDER`, `ADMIN`), con visualización de contratos, gestión de inventario y gráficos estadísticos.

### Preguntas de repaso
1. **¿Cómo maneja la SPA la persistencia de autenticación y los tokens expirados?**
   Almacena el Access Token en memoria/almacenamiento de sesión y el Refresh Token en `sessionStorage`. Cuando una petición recibe un código 401, la SPA invoca automáticamente `/api/auth/refresh` para renovar el token y reintenta la operación sin interrumpir al usuario.
2. **¿Por qué se prefiere Nginx como servidor web y reverse proxy en producción?**
   Porque Nginx sirve los archivos estáticos (HTML, CSS, JS, imágenes) con mínimo consumo de recursos y delega las peticiones `/api/` al backend Spring Boot por red interna, resolviendo las restricciones de CORS en el navegador al compartir el mismo origen.
3. **¿Qué ventaja tiene un diseño responsive con CSS moderno y CSS Custom Properties?**
   Permite centralizar la paleta de colores, tipografías y espaciados en variables CSS reutilizables, adaptando fluidamente la disposición desde dispositivos móviles hasta pantallas de alta resolución.

### Errores comunes de junior evitados
- Guardar contraseñas de usuarios en el almacenamiento local del navegador (`localStorage`).
- Recargar la página completa en cada acción del usuario perdiendo el estado de la aplicación.
- No mostrar retroalimentación visual (spinners, alertas de error) durante peticiones de red asíncronas.

---

## Fase 9 — Suite Exhaustiva de Pruebas Automatizadas

### Conceptos clave
- **Pruebas de Arquitectura con ArchUnit:** Pruebas unitarias que analizan las dependencias estructurales a nivel de paquetes, garantizando que el diseño hexagonal y la pureza del dominio se preserven a lo largo del tiempo.
- **Pruebas de Integración con MockMvc:** Validación de los controladores REST simulando peticiones HTTP reales, validación de encabezados JWT, serialización JSON y códigos de estado sin requerir levantar un servidor HTTP completo en un puerto físico.
- **Pruebas de Ciclo de Vida Completo (End-to-End):** Pruebas que validan el flujo integrado de negocio: catálogo $\to$ solicitud de reserva $\to$ confirmación $\to$ liquidación y pago $\to$ consulta de métricas de BI.

### Preguntas de repaso
1. **¿Qué diferencia existe entre una prueba unitaria pura de dominio y una prueba de integración MockMvc?**
   La prueba de dominio prueba una clase Java aislada (ej. `Reservation.java`) en microsegundos sin contexto de Spring. MockMvc prueba la integración del controlador, validadores de entrada, serializadores JSON y filtros de Spring Security.
2. **¿Cómo verifica ArchUnit que un agregador no importe entidades de persistencia?**
   ArchUnit inspecciona el classpath de clases compiladas y evalúa reglas declarativas como:
   `noClasses().that().resideInAPackage("..domain..").should().dependOnClassesThat().resideInAPackage("..infrastructure..").check(classes);`
3. **¿Por qué es indispensable contar con una colección de Postman versionada?**
   Porque proporciona a los equipos de frontend, QA y revisores una forma inmediata y reproducible de interactuar con todos los endpoints de la API con ejemplos listos, variables de entorno y autenticación preconfigurada.

### Errores comunes de junior evitados
- Escribir pruebas que dependan del orden de ejecución o del estado previo de una base de datos externa.
- Omitir pruebas para los casos negativos (credenciales incorrectas, datos de entrada inválidos, recursos inexistentes).
- Depender exclusivamente de pruebas manuales lentas e irreproducibles.

---

## Fase 10 — Documentación, Despliegue y Revisión Final

### Conceptos clave
- **Trazabilidad y Evidencia de Cumplimiento:** Cada requerimiento del enunciado y cada concepto teórico de las guías se mapea a un archivo y línea de código específicos en `MATRIZ_CUMPLIMIENTO.md`.
- **Estrategias de Despliegue Dual (Docker y Tomcat WAR):** La aplicación está preparada para ejecutarse como contenedor inmutable con Docker Compose o como archivo WAR desplegable en servidores Tomcat institucionales gracias a `SpringBootServletInitializer`.
- **Auditoría y Convenciones Git (Conventional Commits):** Historial de control de versiones limpio y descriptivo (`feat:`, `fix:`, `docs:`, `test:`, `refactor:`) que facilita la revisión de cambios por pares.

### Preguntas de repaso
1. **¿Qué papel cumple `SpringBootServletInitializer` en el despliegue WAR tradicional?**
   Sirve como enlace entre el contenedor de servlets externo (Tomcat) y el contexto de Spring Boot, permitiendo a Tomcat inicializar la aplicación invocando el método `configure()` cuando se despliega el WAR en el directorio `webapps`.
2. **¿Cómo asegura Docker Compose la inicialización ordenada mediante `healthcheck`?**
   El servicio de la aplicación backend declara `depends_on: postgres: condition: service_healthy`, asegurando que Spring Boot solo intente conectarse y ejecutar las migraciones Flyway cuando PostgreSQL haya superado con éxito el comando `pg_isready`.
3. **¿Qué valor aporta un registro formal de decisiones arquitectónicas (ADR)?**
   Permite a cualquier desarrollador presente o futuro comprender el contexto técnico, las limitaciones y las alternativas que llevaron a elegir una tecnología (como PostgreSQL 16 sobre MySQL o Java 21 sobre versiones legadas), defendiendo las decisiones con fundamentos sólidos.

### Errores comunes de junior evitados
- Dejar el README vacío o con instrucciones genéricas sin comandos verificados.
- Olvidar sincronizar los archivos de migración SQL con las entidades JPA.
- Desplegar en producción con perfiles o configuraciones de depuración locales activas.
