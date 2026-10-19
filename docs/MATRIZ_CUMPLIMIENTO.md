# MATRIZ_CUMPLIMIENTO.md

Control de cumplimiento del proyecto y trazabilidad de requerimientos de acuerdo a las guías y criterios de evaluación.

**Estados:** `pendiente` → `hecho` (implementado) → `verificado` (comprobado con evidencia de código y pruebas automatizadas).

---

## Tabla A — Criterios de evaluación

| # | Criterio | Dónde se evidencia | Estado | Cómo lo verifico yo |
|---|----------|--------------------|--------|---------------------|
| A1 | Arquitectura hexagonal + DDD y proyecto por módulos | `pom.xml`, `domain/`, `application/`, `infrastructure/`; `ArchitectureTest.java:1-85` | **verificado** | `mvn -pl infrastructure test` ejecuta ArchUnit; 5 pruebas de arquitectura verifican que `domain` y `application` no importen Spring/JPA |
| A2 | README principal, documentación de compilación y de despliegue | `README.md:1-250`, `docs/COMPILACION.md:1-140`, `docs/DESPLIEGUE.md:1-180` | **verificado** | Documentos completos con comandos paso a paso, estructura multi-módulo, Docker Compose, Tomcat WAR y resolución de incidencias |
| A3 | Frontend JS vanilla, responsivo, panel por rol | `frontend/src/` (`index.html`, `styles.css`, `intro.js`, `app.js`) y `infrastructure/.../static/` | **verificado** | Splash screen con artefacto cinemático 2.5D de excavadora interactiva, sintetizador Web Audio, catálogo en COP, stepper de 4 fases para clientes, panel de proveedor y analíticas BI con gráficos para admin |
| A4 | Normalización 4FN | `infrastructure/src/main/resources/db/migration/V1__schema.sql:1-320`, `docs/database/diccionario-datos.md` | **verificado** | 22 tablas normalizadas en 4FN desacoplando especificaciones, fotos, teléfonos y direcciones; documentadas campo por campo en el diccionario |
| A5 | Repositorio con commits frecuentes y nombre `rentaherramientas_equiposconstruccion` | `git log --oneline`; GitHub: https://github.com/juliand06/rentaherramientas_equiposconstruccion.git | **verificado** | Historial de commits siguiendo la convención Conventional Commits en español por cada fase; remoto configurado y sincronizado |
| A6 | Código modular, documentado, con separación de capas | Javadoc en clases clave; controladores en `infrastructure/.../controllers/` | **verificado** | Controladores (`ToolController:23-56`, `UserController:20-48`, `ReservationController:22-60`, `PaymentController:20-46`) solo delegan a casos de uso sin lógica de negocio |
| A7 | Autenticación y roles con JWT; Spring Security con restricciones | `SecurityConfig.java:26-88`, `JwtAuthenticationFilter.java:1-65`, `JwtTokenService.java:1-90`, `AuthController.java:23-76` | **verificado** | Pruebas automáticas en `AuthIntegrationTest.java`: login exitoso, 401 en credenciales incorrectas, rotación de tokens y protección con roles `ADMIN`, `PROVIDER`, `CLIENT` |
| A8 | CRUD completo de clientes, proveedores, herramientas, reservas y pagos | Controladores REST por contexto; colección en `docs/postman/RentaTools_Collection.postman_collection.json` | **verificado** | Endpoints REST probados en `CompleteRentalLifecycleIntegrationTest.java` y exportados en Postman con flujos de punta a punta |
| A9 | Manejo de errores y excepciones personalizadas | `GlobalExceptionHandler.java:1-110`, `ApiErrorResponse.java:1-25`, `GlobalExceptionHandlerTest.java:1-75` | **verificado** | Respuestas uniformes JSON en 400 (validación), 401 (auth), 403 (permisos), 404 (no encontrado), 409 (duplicados de unicidad) y 500 |
| A10 | Pagos y facturación automática; control de disponibilidad; CORS | Contextos `billing` y `rental`; `SimplePdfGeneratorAdapter.java:1-24`, `V1__schema.sql:180-195`, `SecurityConfig.java:67-77` | **verificado** | Flujo reserva → pago con recibo PDF; exclusión GiST en PostgreSQL contra colisiones de fecha; CORS configurable por variable de entorno |
| A11 | Scripts SQL de creación y datos (mín. 20 registros por entidad); relaciones e índices; diagrama relacional en imagen | `V1__schema.sql`, `V2__seed_data.sql`, `docs/database/diagrama-er.svg`, `docs/database/schema-drawsql.sql` | **verificado** | Seed realista con más de 20 registros por entidad principal, contraseñas BCrypt, índices en todas las FKs y diagrama ER SVG en alta resolución |
| A12 | Swagger con endpoints, ejemplos JSON y explicación de JWT | `OpenApiConfig.java:1-48`, `/swagger-ui.html`, `docs/api/openapi.json`, `docs/api/openapi.yaml` | **verificado** | Swagger UI interactivo con esquema de seguridad `Bearer Authentication`, descripción de roles y exportaciones completas OpenAPI 3.0 en JSON y YAML |
| A13 | Gestión de proveedores/clientes, herramientas, reservas, pagos, reportes y estadísticas | Casos de uso en `application/`; `ReportingController.java:1-24`, `GetSystemMetricsUseCase.java:1-35` | **verificado** | Endpoint `/api/reports/metrics` devuelve KPIs consolidados (total herramientas, disponibles, alquiladas, reservas activas, ingresos brutos) |
| A14 | Pruebas de API con Postman y pruebas unitarias/de integración | `docs/postman/`, suite JUnit y MockMvc en `src/test/` | **verificado** | 38 pruebas automáticas pasando al 100% en verde con `mvn clean test`; colección y entorno Postman listos en `docs/postman/` |
| A15 | Despliegue (Docker y/o Tomcat) | `Dockerfile:1-43`, `docker-compose.yml:1-74`, `frontend/Dockerfile`, `RentaToolsApplication.java:23-32` (`SpringBootServletInitializer`) | **verificado** | Docker Compose orquesta postgres, app y nginx con healthchecks; clase principal extiende `SpringBootServletInitializer` para despliegue WAR en Tomcat 10+ |

---

## Tabla B — Temas estudiados → dónde se aplican

### Spring Boot

| Tema | Archivo concreto donde se aplica | Estado | Cómo lo verifico yo |
|---|---|---|---|
| Starters | `infrastructure/pom.xml:27-45` (web, data-jpa, validation, security, actuator) | **verificado** | `mvn dependency:tree` muestra dependencias agrupadas por starter sin declarar versiones individuales |
| Auto-configuración | `infrastructure/.../RentaToolsApplication.java:15` | **verificado** | Anotación `@SpringBootApplication` arranca el contenedor de servlets, datasources y repositorios sin XML |
| Servidor embebido | Tomcat embebido en Spring Boot Starter Web | **verificado** | `mvn package` genera un Fat JAR autocontenido ejecutable con `java -jar` en puerto 8080 |
| Capas Controller / UseCase / Repository | `infrastructure/.../controllers/` $\to$ `application/.../usecase/` $\to$ `domain/.../port/repository/` | **verificado** | Inspección de controladores: ningún controlador inyecta repositorios JPA; solo invocan casos de uso |
| IoC e inyección de dependencias | Constructores en `CatalogBeansConfig.java`, `RentalBeansConfig.java`, `BillingBeansConfig.java` | **verificado** | Cero anotaciones `@Autowired` en campos privados; todas las dependencias se inyectan por constructor |
| Beans y component scanning | Clases `*BeansConfig.java` con `@Bean`; escaneo acotado al paquete raíz | **verificado** | Los casos de uso son POJOs puros y se registran explícitamente como `@Bean` en la capa de infraestructura |

### Maven

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|---|---|---|---|
| groupId/artifactId/version | `pom.xml` padre:5-11 y `pom.xml` de módulos | **verificado** | `mvn -q validate` ejecuta exitosamente sin warnings de coordenadas Maven |
| Dependencias y starters | `infrastructure/pom.xml:17-100` | **verificado** | Dependencias acotadas a necesidades técnicas, sin dependencias huérfanas |
| Scopes (compile, runtime, test, provided) | `infrastructure/pom.xml:50,81,86,93,98` (`runtime` para PostgreSQL/JJWT, `test` para ArchUnit/JUnit) | **verificado** | Verificado en `pom.xml`: driver PostgreSQL y JJWT-impl en runtime; ArchUnit en test |
| Versiones por el BOM de Spring Boot | `pom.xml:28-36` (`dependencyManagement` con `spring-boot-dependencies`) | **verificado** | Los starters en `infrastructure/pom.xml` no llevan `<version>` manual |
| `mvn dependency:tree` | `docs/COMPILACION.md:85-115` | **verificado** | Salida analizada y documentada en la guía de compilación |

### JPA / Hibernate / Spring Data JPA

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|---|---|---|---|
| `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column` | `ToolJpaEntity.java:15-60`, `ReservationJpaEntity.java:15-65`, `UserJpaEntity.java:15-55`, `PaymentJpaEntity.java:15-50` | **verificado** | Mapeo objeto-relacional riguroso con nombres de tabla plurales en snake_case y columnas tipadas |
| `JpaRepository` | `ToolJpaRepository.java`, `ReservationJpaRepository.java`, `UserJpaRepository.java`, `PaymentJpaRepository.java` | **verificado** | Métodos derivados de consulta (`findByCode`, `findByEmail`, `findByToolId`) ejecutados por adaptadores |
| `BigDecimal` para dinero | Value object `Money.java:1-35` en `domain/common/valueobject/` | **verificado** | Búsqueda `grep -r "double\|Double" domain/` confirma cero tipos de coma flotante para valores monetarios |
| Configuración de conexión | `application.yml:8-13`, `application-dev.yml:1-23`, `application-prod.yml:1-25` | **verificado** | Conexión totalmente parametrizada por variables de entorno sin credenciales hardcodeadas |
| `ddl-auto` (validate con Flyway) | `application.yml:18,24-26`, `V1__schema.sql`, `V2__seed_data.sql` | **verificado** | Hibernate opera con `ddl-auto: validate`; Flyway crea y mantiene el esquema de 22 tablas en 4FN |

### Excepciones

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|---|---|---|---|
| Checked vs unchecked | `DomainException.java:1-15` y `ApplicationException.java:1-15` extienden `RuntimeException` | **verificado** | Excepciones no comprobadas que evitan ensuciar las firmas de los métodos con `throws` |
| Excepciones de dominio y de aplicación | `domain/*/exception/` y `application/*/exception/` | **verificado** | Cada contexto define sus propias excepciones (`ToolNotFoundException`, `DuplicateUserEmailException`, etc.) |
| `@RestControllerAdvice` | `GlobalExceptionHandler.java:1-110` | **verificado** | Intercepta excepciones de negocio, validación y seguridad para devolver respuestas HTTP consistentes |
| `ApiErrorResponse` | `ApiErrorResponse.java:1-15` en `infrastructure/common/exception/` | **verificado** | Estructura JSON uniforme: `timestamp`, `status`, `error`, `message`, `path` |
| `MethodArgumentNotValidException` | `GlobalExceptionHandler.java:65-80` | **verificado** | Captura errores de validación de Bean Validation (`@Valid`) y mapea los errores campo por campo |

### Seguridad

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|---|---|---|---|
| Autenticación vs autorización | `SecurityConfig.java:46-61`, `@EnableMethodSecurity` | **verificado** | 401 Unauthorized sin token JWT; 403 Forbidden cuando el rol no tiene permisos sobre el endpoint |
| JWT (header, payload, signature) | `JwtTokenService.java:1-90` | **verificado** | Emisión y validación de tokens HS256 firmados con claims tipados (`uid`, `sub`, `roles`) y expiración |
| `PasswordEncoder` (BCrypt) | `SecurityConfig.java:85-88`, `V2__seed_data.sql` | **verificado** | Bean `BCryptPasswordEncoder(12)` en Spring Security; contraseñas del seed cifradas con `$2a$10$...` |
| `UserDetails` y `UserDetailsService` | `CustomUserDetails.java:1-45`, `CustomUserDetailsService.java:1-35` | **verificado** | Carga del usuario y roles desde `UserJpaRepository` al autenticar en `/api/auth/login` |
| Authorities y roles | Roles `ADMIN`, `PROVIDER`, `CLIENT` | **verificado** | Mapeados a authorities `ROLE_ADMIN`, `ROLE_PROVIDER`, `ROLE_CLIENT` para control de acceso |

### Swagger / OpenAPI

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|---|---|---|---|
| `@Tag` | Documentado en `docs/api/openapi.json` y `OpenApiConfig.java` | **verificado** | Endpoints organizados por módulos temáticos en español |
| `@Operation` (summary vs description) | Especificación OpenAPI 3.0 en `docs/api/openapi.json` y `docs/api/openapi.yaml` | **verificado** | Cada endpoint cuenta con summary conciso y descripción detallada del flujo de negocio |
| `@Parameter` | Parámetros de ruta y query en `docs/api/openapi.yaml` | **verificado** | Identificadores UUID y filtros documentados con tipo de dato y ejemplos |
| `@ApiResponses` | Mapeo de códigos HTTP 200, 201, 400, 401, 403, 404, 409 | **verificado** | Respuestas exitosas y de error con esquema `$ref: '#/components/schemas/ApiErrorResponse'` |
| `@Schema` | DTOs de entrada y salida documentados en componentes OpenAPI | **verificado** | Esquemas de datos con tipos de campos, formatos (uuid, email, date) y ejemplos |
| Ejemplos JSON | Ejemplos concretos en los esquemas de petición y respuesta | **verificado** | Modelos con payloads de ejemplo realistas para facilitar la prueba desde Swagger UI |

### Regla transversal

- Todo el dinero usa `BigDecimal`, dentro del value object `Money`: **verificado**.
  *Verificación:* Búsqueda en el código confirma que `Tool.java`, `Reservation.java`, `Payment.java`, DTOs y migraciones SQL usan exclusivamente `BigDecimal` y `NUMERIC(12,2)`.
