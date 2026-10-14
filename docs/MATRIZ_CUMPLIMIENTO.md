# MATRIZ_CUMPLIMIENTO.md

Control de cumplimiento del proyecto. Se actualiza en cada cierre de fase.

**Estados:** `pendiente` → `hecho` (implementado) → `verificado` (comprobado con evidencia).

> No existe `docs/referencia-profesor/rubrica.*`, por eso la Tabla A usa los criterios del enunciado.
> Las rutas de evidencia son las **planeadas**; se confirman al implementar cada fase.

---

## Tabla A — Criterios de evaluación

| # | Criterio | Dónde se evidencia | Estado | Cómo lo verifico yo |
|---|----------|--------------------|--------|---------------------|
| A1 | Arquitectura hexagonal + DDD y proyecto por módulos | `pom.xml`, `domain/`, `application/`, `infrastructure/`; `ArchitectureTest` | hecho | `mvn -pl infrastructure test` pasa ArchUnit; 5 pruebas de arquitectura pasando |
| A2 | README principal, documentación de compilación y de despliegue | `README.md`, `docs/COMPILACION.md`, `docs/DESPLIEGUE.md` | pendiente | Sigo los pasos de cada doc en limpio y funcionan |
| A3 | Frontend JS vanilla (Bootstrap si lo autoriza el trainer), responsivo, panel por rol | `frontend/` (páginas admin, provider, client) | pendiente | Abro cada panel en móvil, tableta y escritorio |
| A4 | Normalización 4FN | `infrastructure/src/main/resources/db/migration/V1__schema.sql`, `docs/database/diccionario-datos.md` | verificado | 22 tablas normalizadas en 4FN; documentadas en el diccionario de datos |
| A5 | Repositorio con commits frecuentes y nombre `rentaherramientas_equiposconstruccion` | `git log`; GitHub: https://github.com/juliand06/rentaherramientas_equiposconstruccion.git | verificado | `git log --oneline` muestra commits con Conventional Commits; remoto enlazado y subido |
| A6 | Código modular, documentado, con separación de capas | Javadoc en clases clave; `AGENTS.md` | pendiente | Reviso que los controladores no tengan lógica de negocio |
| A7 | Autenticación y roles con JWT; Spring Security con restricciones | `infrastructure/.../security/`, `@PreAuthorize` en controladores | pendiente | Pruebo con y sin token, y con rol incorrecto (401 y 403) |
| A8 | CRUD completo de clientes, proveedores, herramientas, reservas y pagos | Controladores REST por contexto; colección Postman | pendiente | Ejecuto POST, GET, PUT y DELETE de cada entidad |
| A9 | Manejo de errores y excepciones personalizadas | `GlobalExceptionHandler`, `ApiErrorResponse`, excepciones por capa | pendiente | Provoco 400, 404, 409 y 500; el JSON es uniforme |
| A10 | Pagos y facturación automática; control de disponibilidad; CORS | Contextos `billing` y `rental`; `CorsConfig` | pendiente | Flujo reserva → pago → factura; reserva solapada es rechazada; CORS por variable |
| A11 | Scripts SQL de creación y datos (mín. 20 registros por entidad); relaciones e índices; diagrama relacional en imagen | `V1__schema.sql`, `V2__seed_data.sql`, `docs/database/diagrama-er.svg` | verificado | `SELECT count(*)` por tabla principal ≥ 20 comprobado en PostgreSQL 16; `diagrama-er.svg` generado |
| A12 | Swagger con endpoints, ejemplos JSON y explicación de JWT | `OpenApiConfig`, `/swagger-ui.html`, `docs/api/openapi.json` | pendiente | Abro Swagger UI, uso Authorize y ejecuto un endpoint |
| A13 | Gestión de proveedores/clientes, herramientas, reservas, pagos, reportes y estadísticas | Casos de uso por contexto; contexto `reporting` | pendiente | Cada reporte devuelve datos coherentes con el seed |
| A14 | Pruebas de API con Postman y pruebas unitarias/de integración | `docs/postman/`, `src/test/` de cada módulo | pendiente | Corro la colección y `mvn clean verify` |
| A15 | Despliegue (Docker y/o Tomcat) | `Dockerfile`, `docker-compose.yml`, WAR | pendiente | `docker compose up` levanta todo y los healthchecks pasan; el WAR corre en Tomcat |

---

## Tabla B — Temas estudiados → dónde se aplican

### Spring Boot

| Tema | Archivo concreto donde se aplica | Estado | Cómo lo verifico yo |
|------|----------------------------------|--------|---------------------|
| Starters | `infrastructure/pom.xml` (web, data-jpa, validation, security) | pendiente | `mvn dependency:tree` |
| Auto-configuración | Clase principal de arranque | pendiente | La app arranca sin XML |
| Servidor embebido | Tomcat embebido en el JAR | pendiente | `java -jar` responde en el puerto |
| Capas Controller / UseCase / Repository | Controladores, `application/*/usecase`, adaptadores | pendiente | Ningún controlador accede al repositorio |
| IoC e inyección de dependencias | Constructores en los `BeansConfig` | pendiente | Sin `@Autowired` en campos |
| Beans y component scanning | `XxxBeansConfig` con `@Bean`; escaneo acotado | pendiente | Sin `scanBasePackages` genérico |

### Maven

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|------|------------------|--------|---------------------|
| groupId/artifactId/version | `pom.xml` padre y de módulos | pendiente | `mvn -q validate` |
| Dependencias y starters | `infrastructure/pom.xml` | pendiente | Sin dependencias sin usar |
| Scopes (compile, runtime, test, provided) | `postgresql` runtime, JUnit test, servlet provided en WAR | pendiente | Reviso cada scope |
| Versiones por el BOM de Spring Boot | `dependencyManagement` con `spring-boot-dependencies` | pendiente | Dependencias de Spring sin `<version>` |
| `mvn dependency:tree` | `docs/COMPILACION.md` | pendiente | Salida incluida en el documento |

### JPA / Hibernate / Spring Data JPA

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|------|------------------|--------|---------------------|
| `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column` | `*JpaEntity.java` | pendiente | `ddl-auto: validate` arranca sin errores |
| `JpaRepository` | `*JpaRepository.java` | pendiente | Pruebas de integración con Testcontainers |
| `BigDecimal` para dinero | Value object `Money` en `domain` | pendiente | `grep -r "double\|Double"` sin resultados en dinero |
| Configuración de conexión | `application-dev.yml`, `application-prod.yml` | pendiente | Variables de entorno, sin credenciales en código |
| `ddl-auto` (validate con Flyway) | `application.yml`, `V1__schema.sql`, `V2__seed_data.sql` | verificado | `validate` verificado contra PostgreSQL 16; Flyway aplicó V1 y V2 |

### Excepciones

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|------|------------------|--------|---------------------|
| Checked vs unchecked | `DomainException` y `ApplicationException` (unchecked) | pendiente | Explicado en `DECISIONES.md` |
| Excepciones de dominio y de aplicación | `domain/*/exception/`, `application/*/exception/` | pendiente | Cada excepción vive en su contexto |
| `@RestControllerAdvice` | `GlobalExceptionHandler` | pendiente | Prueba MockMvc por tipo de error |
| `ApiErrorResponse` | `infrastructure/common/` | pendiente | Todos los errores tienen el mismo JSON |
| `MethodArgumentNotValidException` | `GlobalExceptionHandler` | pendiente | POST inválido devuelve errores por campo |

### Seguridad

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|------|------------------|--------|---------------------|
| Autenticación vs autorización | `SecurityConfig`, `@PreAuthorize` | pendiente | 401 sin token, 403 con rol incorrecto |
| JWT (header, payload, signature) | `JwtTokenService` | pendiente | Decodifico un token y reviso sus claims |
| `PasswordEncoder` (BCrypt) | `SecurityConfig`, `V2__seed_data.sql` | hecho | Las contraseñas del seed son hashes BCrypt ($2a$10$...) |
| `UserDetails` y `UserDetailsService` | Clases de `identity` en infraestructura | pendiente | Login carga el usuario desde BD |
| Authorities y roles | `ADMIN`, `PROVIDER`, `CLIENT` | pendiente | Cada rol accede solo a sus rutas |

### Swagger / OpenAPI

| Tema | Archivo concreto | Estado | Cómo lo verifico yo |
|------|------------------|--------|---------------------|
| `@Tag` | Interfaces `XxxApi` | pendiente | Tags en español en Swagger UI |
| `@Operation` (summary vs description) | Interfaces `XxxApi` | pendiente | Cada endpoint tiene ambos y el rol permitido |
| `@Parameter` | Interfaces `XxxApi` | pendiente | Parámetros con descripción y ejemplo |
| `@ApiResponses` | Interfaces `XxxApi` | pendiente | Códigos 200/201, 400, 401, 403, 404, 409 según aplique |
| `@Schema` | DTOs REST y `ApiErrorResponse` | pendiente | Modelos con descripción y ejemplo |
| Ejemplos JSON | `@ExampleObject` | pendiente | Ejemplos visibles en Swagger UI |

### Regla transversal

- Todo el dinero usa `BigDecimal`, dentro del value object `Money`. Estado: **pendiente**.
  Verificación: búsqueda de `double`/`float` en el código y revisión de `NUMERIC` en las migraciones.
