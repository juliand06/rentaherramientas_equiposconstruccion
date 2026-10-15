# PLAN.md — Fases y checklist

Se marca una fase como hecha solo cuando: compila, pasan las pruebas, `docker compose up`
levanta (cuando aplique), se actualiza `MATRIZ_CUMPLIMIENTO.md` y se hace commit.

Antes de cada fase se explica qué se hará y por qué (5-10 líneas).

## Fase 0 — Lectura, análisis y plan
- [x] Enunciado y guías leídas (cobertura detallada en `FASE_0_ENTREGA.md`)
- [x] Repos `demo-ddd` y `security` leídos
- [x] `AGENTS.md`, `DECISIONES.md`, `APRENDIZAJE.md`, `MATRIZ_CUMPLIMIENTO.md`
- [x] `FASE_0_ENTREGA.md` y `PLAN.md`
- [x] **Aprobación del estudiante** (bloquea la Fase 1)

## Fase 1 — Estructura Maven multi-módulo + Docker + PostgreSQL
Antes: leer completa la guía de Dependencias y Beans y la de Spring Boot.
- [x] `pom.xml` padre (BOM, Java 21) y módulos `domain`, `application`, `infrastructure`, `frontend`
- [x] Verificar versiones reales: Boot 4.0.8, springdoc 3.1.1, jjwt 0.12.7, Flyway
- [x] Clase principal limpia, perfiles `dev`/`prod`, `.env.example`
- [x] `Dockerfile` multi-stage, `docker-compose.yml` (postgres, app, nginx) con healthchecks y volúmenes
- [x] Prueba ArchUnit de la regla `infrastructure → application → domain` (5 pruebas pasando)
- [x] Compilación limpia `mvn clean verify` pasando
- [x] Matriz actualizada, commit y push a GitHub

## Fase 2 — Esquema, migraciones y archivos drawSQL
Antes: leer completas las guías JPA y Gestión de BD 1 y 2.
- [x] `V1__schema.sql` (22 tablas, 4FN, PK/FK/UNIQUE/CHECK, índices, `EXCLUDE USING gist`)
- [x] `V2__seed_data.sql` (20 o más por entidad principal, roles, usuarios de prueba con BCrypt)
- [x] `docs/database/`: `schema-drawsql.sql`, `schema.dbml`, `diagrama-er.mmd` y `.svg`, `diccionario-datos.md`, `INSTRUCCIONES-DRAWSQL.md`
- [x] `schema-drawsql.sql` ejecuta sin errores en PostgreSQL limpio
- [x] Migraciones corren con Flyway; `ddl-auto: validate` listo
- [x] Matriz actualizada, commit

## Fase 3 — Dominio
- [x] `common`: `AggregateRoot`, `DomainEvent`, `DomainException`
- [x] Value objects: `Money` (BigDecimal), `DateRange`, `Email`, IDs (`ToolId`, `UserId`, `ReservationId`)
- [x] Agregados por contexto: `identity` (`User`), `catalog` (`Tool`), `rental` (`Reservation`) con invariantes y fábricas
- [x] Eventos: `ReservationConfirmed`
- [x] Puertos de repositorio por agregado (`ToolRepository`, `UserRepository`, `ReservationRepository`)
- [x] Pruebas unitarias de dominio (JUnit puro) pasando al 100% (13 tests) + 5 ArchUnit
- [ ] Matriz actualizada, commit

## Fase 4 — Aplicación y persistencia (rebanada vertical primero)
Antes: leer completas las guías de Excepciones (secciones 34-55).
- [x] **4a — Rebanada vertical `catalog` (herramientas)** de punta a punta: use cases, entidad, mapper, adaptador, BeansConfig, controlador mínimo, pruebas.
- [x] 4b — Replicar el patrón en `identity`, `rental`, `billing` (casos de uso, entidades JPA, mappers, repositorios y controladores REST)
- [x] `DomainEventPublisher` y ejecutor desacoplado con Spring Events
- [x] Pruebas de casos de uso con fakes y validación ArchUnit pasando al 100% (29 tests totales)
- [x] Matriz actualizada, commits realizados por contexto


## Fase 5 — Seguridad JWT
Antes: leer completo el resto de la guía Security + JWT.
- [x] `SecurityFilterChain`, `JwtAuthenticationFilter`, `JwtTokenService`, `UserDetails`, `UserDetailsService`
- [x] Registro, login, refresh (con rotación de tokens)
- [x] `@EnableMethodSecurity`, control de sesiones `STATELESS` y CORS por variable de entorno
- [x] Pruebas de compilación y arquitectura ArchUnit pasando al 100%
- [x] Matriz actualizada, commit


## Fase 6 — API REST + excepciones + Swagger
- [x] Controladores por contexto (`ToolController`, `UserController`, `ReservationController`, `PaymentController`, `AuthController`)
- [x] `GlobalExceptionHandler`, `ApiErrorResponse` (traducción de 400, 401, 403, 404, 409, 500)
- [x] `OpenApiConfig` Swagger UI 3.x con autenticación Bearer JWT
- [x] Pruebas unitarias de excepciones pasando al 100% (32 pruebas totales en el reactor)
- [x] Matriz actualizada, commit


## Fase 7 — Facturación, PDF y reportes
- [x] Pasarela simulada (puerto y adaptador falso); pagos e historial; vencimiento de reservas
- [x] Factura automática y PDF descargable
- [x] Subida y almacenamiento de imágenes
- [x] Reportes y métricas detrás de un puerto de aplicación (`ReportingPort`, `GetSystemMetricsUseCase`, `ReportingController`)
- [x] Matriz actualizada, commit


## Fase 8 — Frontend
- [x] Splash screen inicial con artefacto interactivo de maquinaria de construcción pesada (cinemática reactiva 2.5D, partículas canvas a 60fps, sintetizador Web Audio industrial, blast doors y transición fluida)
- [x] Login/registro, JWT con persistencia y selector dinámico de roles de prueba (Cliente, Proveedor, Admin)
- [x] Catálogo reactivo con filtros de categoría industrial, buscador en tiempo real y liquidación en pesos colombianos (COP)
- [x] Modal interactivo de reserva con cálculo dinámico de días, tarifas diarias, depósito reembolsable y dirección de obra
- [x] Centro de control del Cliente con stepper de 4 fases (`Solicitada` -> `Confirmada/Paga` -> `En Obra` -> `Devuelta`), pago simulado y visor de contratos digitales
- [x] Panel de Proveedor con formulario de registro de maquinaria pesada e inventario propio
- [x] Tablero de Analítica & BI para Admin con KPIs en vivo, gráfico de barras de ingresos por categoría y gráfico circular (donut) de estado de flota
- [x] Servido tanto por Nginx (Docker) como por Spring Boot static resources (`/index.html`)
- [x] Matriz actualizada, commit

## Fase 9 — Pruebas
- [x] Unitarias y de arquitectura (38 pruebas en el reactor con MockMvc, fakes y ArchUnit)
- [x] Test de flujo completo: catálogo → reserva → pago → confirmación → métricas BI (`CompleteRentalLifecycleIntegrationTest.java`)
- [x] Test de autenticación e integración JWT: login, credenciales incorrectas y rotación de tokens (`AuthIntegrationTest.java`)
- [x] Colección Postman con flujos completos y variables de entorno (`docs/postman/RentaTools_Collection.postman_collection.json`)
- [x] `mvn clean verify` en verde (100% pruebas pasando, JAR empaquetado exitosamente)
- [x] Matriz actualizada, commit

## Fase 10 — Documentación y revisión final
- [ ] `README.md`, `COMPILACION.md`, `DESPLIEGUE.md` (Docker y Tomcat/WAR)
- [ ] Auditoría: cada guía y cada repo con evidencia (archivo y línea)
- [ ] Reporte final basado en la matriz: hecho y verificado, pendiente, qué mejoraría un senior
- [ ] Commit final y push
