# FASE 0 — Entrega: lectura, análisis y plan

Fecha: 2026-10-13. Sin código de negocio. Pendiente de aprobación del estudiante.

## 0. Cobertura de lectura (honesta)

| Material | Estado de lectura |
|----------|-------------------|
| Enunciado (`ejercicioDemo`) | Completo |
| Guía Swagger | Completa |
| Guías JPA / Gestión de BD (1 y 2) | Leídas hasta la línea ~3200 y por índice de secciones (las dos versiones repiten estructura) |
| Guía Excepciones | Leída hasta la sección 33; secciones 34-55 por índice (logging, arquitectura, buenas prácticas) |
| Guía Security + JWT | Leída hasta la sección 37; resto por índice |
| Guía Spring Boot y guía Dependencias/Beans | Por índice de secciones |
| Repo `demo-ddd` | Completo (31 archivos Java, poms, YAML, migración) |
| Repo `security` | Poms, YAML, seguridad, errores, OpenAPI, dominio y casos de uso. Controladores, entidades y mapper leídos solo parcialmente |

> No existe `rubrica.*` en `docs/referencia-profesor/`.
> Las secciones por índice las leeré completas **antes de la fase donde se aplican** (ver PLAN.md).
> `docs/referencia-profesor/` tiene las guías en un solo archivo unificado, no 10 archivos separados.

---

## (a) Resumen de requisitos

**Producto:** plataforma de renta de herramientas y equipos de construcción con tres roles.

| Rol | Hace |
|-----|------|
| ADMIN | Gestiona usuarios; ve historial global; supervisa pagos, facturas, devoluciones y daños; reportes con gráficos |
| PROVIDER | Inventario con imágenes; acepta o rechaza reservas; confirma devoluciones; reporta daños; genera facturas |
| CLIENT | Explora y busca; reserva con fechas; paga en línea; descarga factura; ve su historial |

**Backend:** Auth (registro, login, refresh, logout) con JWT; CRUD de usuarios, proveedores, clientes, herramientas, reservas y pagos; disponibilidad validada antes de confirmar; estados `PENDING, CONFIRMED, DELIVERED, RETURNED, CANCELLED, EXPIRED`; entregas, devoluciones y daños; pagos con pasarela simulada; facturación automática con PDF; subida de imágenes; 5 reportes; paginación y filtros; errores uniformes; CORS por variable de entorno.

**Base de datos:** PostgreSQL 16, 4FN, 22 tablas, Flyway (V1 esquema, V2 datos con 20 o más registros por entidad principal), `EXCLUDE USING gist` contra solapamiento, `ddl-auto: validate`.

**Frontend:** HTML/CSS/JS vanilla con Bootstrap 5; panel por rol; JWT con refresh; responsivo.

**Documentación obligatoria:** README, COMPILACION, DESPLIEGUE, Swagger (+ export), diagrama relacional y diccionario, Postman, DECISIONES, APRENDIZAJE, MATRIZ.

**Calidad:** SOLID, sin credenciales en código, perfiles dev/prod, logging, pruebas unitarias, de integración y de flujo completo, Conventional Commits.

---

## (b) Contradicciones detectadas y cómo las resolví

| # | Contradicción | Resolución | Dónde se documenta |
|---|---------------|------------|--------------------|
| 1 | Enunciado dice **MySQL**; tu stack y las guías, **PostgreSQL** | PostgreSQL 16 (manda tu decisión, sección 1) | ADR-001, README |
| 2 | Enunciado: "Java 7 o superior" (errata), "Java 17 mínimo" y "Java 21"; `demo-ddd` usa Java 25 | Java 21 | ADR-002 |
| 3 | Enunciado pide capas "Controllers, Services, Repositories" con `@Service`; tú pides `application` pura | Los casos de uso hacen de "Service"; los beans se declaran en `BeansConfig`. Cubro `@Service`/`@Component` en infraestructura (adaptadores, servicios técnicos) | ADR + Tabla B |
| 4 | Guías JPA: `ddl-auto=update`, `Long` con `IDENTITY` | `validate` + Flyway; UUID nativo | ADR |
| 5 | Guías de seguridad: `UserDetails`, `UserDetailsService`, `AuthenticationManager`; el repo `security` no los usa (filtro propio) | Uso ambos: `UserDetailsService` para el login y un filtro que arma la autenticación desde los claims del JWT | ADR |
| 6 | `security` guarda `sub = userId`; la guía usa `sub = email` | `sub = userId` (UUID) y `email` como claim; no cambia con el email | ADR |
| 7 | Enunciado: "Apache Tomcat" y servidor embebido; tú: Docker principal | Docker principal y WAR en Tomcat como alternativa documentada | DESPLIEGUE |
| 8 | Enunciado: Bootstrap o Tailwind "según autorice el trainer" | Bootstrap 5 aislado y fácil de quitar | ADR |
| 9 | Tu lista de tablas dice `usuarios`; tu regla dice nombres en inglés | `users` | ADR |
| 10 | Pides módulo Maven `frontend`, pero el frontend es estático | Módulo `frontend` que empaqueta archivos estáticos para nginx, sin Node | ADR |
| 11 | Tabla de datos de "teléfonos/direcciones" sin dueño definido | Ver pregunta P2 | Pendiente |
| 12 | `security` README dice Boot 3.3; el `pom` usa 4.0.2; `demo-ddd` pide `spring-boot-flyway` | Verifico versiones reales al crear el `pom` (ver riesgo R1) | README |

---

## (c) Qué haré igual y qué haré distinto

### Respecto a `demo-ddd`

| Igual | Distinto |
|-------|----------|
| Multi-módulo `domain / application / infrastructure` con BOM `spring-boot-dependencies` | Java 21 (no 25); springdoc 3.x |
| `AggregateRoot` con `recordEvent / domainEvents / clearDomainEvents` | Los eventos se despachan de verdad (puerto `DomainEventPublisher`) |
| Agregado con constructor privado, `register/create` y `restore` | Invariantes reales; `Clock` inyectado (no `LocalDateTime.now()`) |
| IDs como `record` sobre UUID con `generate()` | Excepciones de dominio dentro de su contexto |
| Casos de uso sin anotaciones, `execute(...)`, constructor con puertos | El caso de uso consulta `existsBy...` antes de guardar |
| Commands, DTOs y `ApplicationException` | Transacciones en infraestructura (adaptadores y ejecutor transaccional) |
| `XxxJpaEntity`, `JpaRepository`, `RepositoryAdapter`, mapper manual | Flyway como única fuente del esquema; `validate` |
| `XxxBeansConfig` con `@Bean` | `@SpringBootApplication` limpio, sin código comentado |
| Fakes en memoria para probar casos de uso | `.gitignore` desde el inicio; `prod` completo; sin contraseñas en YAML |
| Controlador que solo traduce | Baja lógica (`active`) donde corresponde; `GlobalExceptionHandler` |

### Respecto a `security`

| Igual | Distinto |
|-------|----------|
| `SecurityFilterChain`, `@EnableMethodSecurity`, stateless, CSRF off | Reglas por rol (`@PreAuthorize` + chain) con ADMIN/PROVIDER/CLIENT |
| `JwtAuthenticationFilter` + `jjwt` 0.12.x | El filtro no traga errores en silencio; responde 401 coherente |
| BCrypt | `extractRoles` no inventa roles con UUID aleatorio: los roles vienen de los claims |
| Refresh token persistido y revocable | Refresh token guardado como hash, no en claro |
| `OpenApiConfig` con esquema Bearer | Sin `server` de producción de ejemplo; textos en español |
| Perfiles `dev` y `prod`, variables `${VAR}` | `jwt.secret` sin valor por defecto en prod; CORS por variable |
| `ErrorResponse` / handler global | `ApiErrorResponse(timestamp,status,error,message,path)`; errores de BD sin detalles internos |
| Excepciones de dominio con `DomainException` | `@Service`/`@Value` fuera de `application`; solo PostgreSQL con Flyway; sin `DataInitializer` (los roles van en V2) |

---

## (d) Borrador de `docs/MATRIZ_CUMPLIMIENTO.md`

Ya creada y commiteada: ver [MATRIZ_CUMPLIMIENTO.md](MATRIZ_CUMPLIMIENTO.md). Tiene la Tabla A (15 criterios) y la Tabla B (33 filas), todas en `pendiente`.

---

## (e) Material recibido → cómo lo aplicaré → dónde quedará

| Material | Cómo lo aplicaré | Dónde quedará |
|----------|------------------|---------------|
| `ejercicioDemo` — roles y funciones | Un caso de uso por acción de cada rol; `@PreAuthorize` por rol | `application/*/usecase`, controladores |
| `ejercicioDemo` — entregables (SQL, diagrama, Swagger, Postman, README) | Cada entregable tiene su fase y su fila en la matriz | `docs/`, `V1/V2`, `docs/postman` |
| `ejercicioDemo` — 20 registros por entidad | Seed realista y coherente | `V2__seed_data.sql` |
| `ejercicioDemo` — Tomcat | WAR con `SpringBootServletInitializer` | `infrastructure` (perfil `war`), `DESPLIEGUE.md` |
| Guía Spring Boot — starters, IoC, beans, scanning, servidor embebido | `BeansConfig` con `@Bean`; escaneo acotado al paquete raíz | `infrastructure/*/config`, `pom.xml` |
| Guía Dependencias y Beans — BOM, scopes, `dependency:tree`, `@Component` vs `@Bean` | BOM en el padre; `postgresql` runtime, JUnit test, servlet `provided` en WAR; `@Component` en adaptadores técnicos, `@Bean` para casos de uso | `pom.xml`, `COMPILACION.md` |
| Guía JPA / Introducción a JPA — `@Entity`, `@Table`, `@Id`, `@Column`, `JpaRepository`, derivadas, JPQL, paginación, `@Transactional` | Entidades `XxxJpaEntity`; derivadas y `@Query`; `Pageable` en listados; `@Transactional` en adaptadores | `infrastructure/*/adapters/out/persistence` |
| Gestión de BD 1 y 2 — conexión, relaciones, `BigDecimal`, `ddl-auto` | `validate` + Flyway; `NUMERIC(12,2)` ↔ `BigDecimal` en `Money`; relaciones `@ManyToOne` | `application-*.yml`, `V1`, entidades |
| Guía Excepciones — dominio/aplicación, `@RestControllerAdvice`, `ApiErrorResponse`, validación, duplicados 409, BD sin detalles, logs separados | Jerarquía por capa; `GlobalExceptionHandler` con todos los casos de la guía | `domain/*/exception`, `application/*/exception`, `infrastructure/common` |
| Guía Security + JWT — `PasswordEncoder`, `UserDetails`, `UserDetailsService`, `AuthenticationManager`, `SecurityFilterChain`, filtro, claims, stateless | Todos aplicados (ver contradicción 5) | `infrastructure/identity/security` |
| Guía Swagger — `@Tag`, `@Operation`, `@Parameter`, `@ApiResponses`, `@Schema`, DTO en vez de entidad, `OpenApiConfig` | Interfaces `XxxApi` documentadas; DTOs con `@Schema`; export | `adapters/in/rest`, `docs/api` |
| Repo `demo-ddd` | Plantilla de estructura y patrones; debilidades corregidas | Todo el backend; `DECISIONES.md` |
| Repo `security` | Plantilla de seguridad, errores, Swagger y perfiles; debilidades corregidas | `infrastructure/identity`, `infrastructure/common` |

---

## Riesgos detectados

| # | Riesgo | Mitigación |
|---|--------|------------|
| R1 | Boot 4 cambió módulos (Flyway, Jackson, Testcontainers) | Compilo y arranco temprano en la Fase 1; si falla, te aviso con alternativa estable |
| R2 | **El daemon de Docker no corre** (falta el socket de Docker Desktop). Sin él no hay `docker compose up` ni Testcontainers | Ver pregunta P1 |
| R3 | PostgreSQL local es la versión 18; el proyecto pide 16 | Uso la imagen `postgres:16` en Docker; no dependo del `psql` local |
| R4 | Sin push: no hay remoto | Ver pregunta P3 |

## Preguntas que necesito que decidas

- **P1 — Docker:** está instalado pero el daemon no corre. ¿Puedes iniciarlo (Docker Desktop o `systemctl`)? Sin él no puedo verificar compose ni Testcontainers, y la regla "verifica con docker compose up" no se cumple.
- **P2 — Teléfonos y direcciones:** propongo que sean de `users` (1:N), y que `providers` y `clients` sean perfiles 1:1 con `users`. Es más limpio que una relación polimórfica, porque mantiene las FK reales. Esto cambia lo que dije antes. ¿Apruebas?
- **P3 — Repo en GitHub:** necesito la URL y tu apellido y nombre, para el nombre `Proyecto_RentaHerramientas_ApellidoNombre`.
- **P4 — Paquete raíz:** propongo `com.rentatools`. ¿Te sirve?
- **P5 — PDF:** propongo OpenPDF. ¿Te sirve?
