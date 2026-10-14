# APRENDIZAJE.md — Guía de estudio por fase

Por cada fase: conceptos clave aplicados, 3 preguntas de repaso tipo sustentación
con respuesta, y errores comunes de junior que se evitaron.

## Fase 0 — Plan y convenciones

### Conceptos clave
- **Arquitectura hexagonal (puertos y adaptadores):** el dominio define interfaces (puertos);
  la infraestructura las implementa (adaptadores). El negocio no conoce la tecnología.
- **Regla de dependencias:** `infrastructure → application → domain`.
- **ADR:** documentar por qué se tomó una decisión, no solo qué se hizo.

### Preguntas de repaso
1. **¿Por qué `application` no usa anotaciones de Spring?**
   Para que los casos de uso no dependan del framework. Se prueban con fakes en memoria
   y se podría cambiar Spring sin tocar el negocio. Los beans se declaran a mano en `BeansConfig`.
2. **¿Qué es un puerto y qué es un adaptador?**
   El puerto es una interfaz que pertenece al dominio o a la aplicación
   (ej. `ToolRepository`). El adaptador la implementa con una tecnología concreta (ej. JPA).
3. **¿Por qué se documentan las desviaciones respecto a los repos del profesor?**
   Porque un cambio sin explicación parece un error. Con el motivo escrito se puede defender
   en la sustentación.

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
> Las siguientes fases se agregan aquí al cerrarse cada una.
