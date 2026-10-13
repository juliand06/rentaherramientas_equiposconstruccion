# PLAN.md — Fases y checklist

Se marca una fase como hecha solo cuando: compila, pasan las pruebas, `docker compose up`
levanta (cuando aplique), se actualiza `MATRIZ_CUMPLIMIENTO.md` y se hace commit.

Antes de cada fase se explica qué se hará y por qué (5-10 líneas).

## Fase 0 — Lectura, análisis y plan
- [x] Enunciado y guías leídas (cobertura detallada en `FASE_0_ENTREGA.md`)
- [x] Repos `demo-ddd` y `security` leídos
- [x] `AGENTS.md`, `DECISIONES.md`, `APRENDIZAJE.md`, `MATRIZ_CUMPLIMIENTO.md`
- [x] `FASE_0_ENTREGA.md` y `PLAN.md`
- [ ] **Aprobación del estudiante** (bloquea la Fase 1)

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
- [ ] `V1__schema.sql` (22 tablas, 4FN, PK/FK/UNIQUE/CHECK, índices, `EXCLUDE USING gist`)
- [ ] `V2__seed_data.sql` (20 o más por entidad principal, roles, usuarios de prueba con BCrypt)
- [ ] `docs/database/`: `schema-drawsql.sql`, `schema.dbml`, `diagrama-er.mmd` y `.svg`, `diccionario-datos.md`, `INSTRUCCIONES-DRAWSQL.md`
- [ ] `schema-drawsql.sql` ejecuta sin errores en PostgreSQL limpio
- [ ] Migraciones corren con Flyway; `ddl-auto: validate` listo
- [ ] Matriz actualizada, commit

## Fase 3 — Dominio
- [ ] `common`: `AggregateRoot`, `DomainEvent`, `DomainException`
- [ ] Value objects: `Money` (BigDecimal), `DateRange`, `Email`, IDs
- [ ] Agregados por contexto: `identity`, `catalog`, `rental`, `billing` con invariantes y `Clock`
- [ ] Eventos: `ReservationConfirmed`, `PaymentRegistered`, `ReturnRegistered`
- [ ] Puertos de repositorio por agregado
- [ ] Pruebas unitarias de dominio (JUnit puro)
- [ ] Matriz actualizada, commit

## Fase 4 — Aplicación y persistencia (rebanada vertical primero)
Antes: leer completas las guías de Excepciones (secciones 34-55).
- [ ] **4a — Rebanada vertical `catalog` (herramientas)** de punta a punta: use cases, entidad, mapper, adaptador, BeansConfig, controlador mínimo, pruebas. **Te la muestro y espero tu visto bueno**
- [ ] 4b — Replicar el patrón en `identity`, `rental`, `billing`
- [ ] `DomainEventPublisher` y ejecutor transaccional
- [ ] Pruebas de casos de uso con fakes; integración de repositorios con Testcontainers
- [ ] Matriz actualizada, commit

## Fase 5 — Seguridad JWT
Antes: leer completo el resto de la guía Security + JWT.
- [ ] `SecurityFilterChain`, `JwtAuthenticationFilter`, `JwtTokenService`, `UserDetails`, `UserDetailsService`
- [ ] Registro, login, refresh (con rotación), logout
- [ ] `@PreAuthorize` por rol; CORS por variable de entorno
- [ ] Pruebas MockMvc: 401, 403 y acceso correcto
- [ ] Matriz actualizada, commit

## Fase 6 — API REST + excepciones + Swagger
- [ ] Controladores por contexto (solo traducen)
- [ ] `GlobalExceptionHandler`, `ApiErrorResponse`
- [ ] `OpenApiConfig`, interfaces `XxxApi` con `@Tag`, `@Operation`, `@ApiResponses`, `@ExampleObject`
- [ ] Export `docs/api/openapi.json` y `openapi.yaml`; Swagger UI carga sin errores
- [ ] Pruebas MockMvc por controlador
- [ ] Matriz actualizada, commit

## Fase 7 — Facturación, PDF y reportes
- [ ] Pasarela simulada (puerto y adaptador falso); pagos e historial; vencimiento de reservas
- [ ] Factura automática y PDF descargable
- [ ] Subida de imágenes
- [ ] 5 reportes detrás de un puerto de aplicación
- [ ] Matriz actualizada, commit

## Fase 8 — Frontend
- [ ] Login/registro, JWT con refresh, redirección por rol
- [ ] Paneles Admin, Proveedor y Cliente; gráficos; responsivo
- [ ] Bootstrap aislado
- [ ] Matriz actualizada, commit

## Fase 9 — Pruebas
- [ ] Unitarias, integración (Testcontainers y MockMvc)
- [ ] Test de flujo completo: reserva → pago → factura → devolución
- [ ] Colección Postman con flujos completos
- [ ] `mvn clean verify` en verde
- [ ] Matriz actualizada, commit

## Fase 10 — Documentación y revisión final
- [ ] `README.md`, `COMPILACION.md`, `DESPLIEGUE.md` (Docker y Tomcat/WAR)
- [ ] Auditoría: cada guía y cada repo con evidencia (archivo y línea)
- [ ] Reporte final basado en la matriz: hecho y verificado, pendiente, qué mejoraría un senior
- [ ] Commit final y push
