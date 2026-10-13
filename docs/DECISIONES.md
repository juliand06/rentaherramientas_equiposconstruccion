# DECISIONES.md — Registro de decisiones (estilo ADR)

Cada decisión indica contexto, alternativas y motivo.
Orden de prioridad cuando hay conflicto: (1) decisiones técnicas del estudiante,
(2) enunciado del profesor, (3) guías y repos del profesor.

## ADR-001 — PostgreSQL 16 en lugar de MySQL
- **Contexto:** el enunciado menciona MySQL. El stack del estudiante y las guías usan PostgreSQL.
- **Alternativas:** MySQL (literal del enunciado); PostgreSQL.
- **Decisión:** PostgreSQL 16.
- **Motivo:** permite `EXCLUDE USING gist` para evitar reservas solapadas, UUID nativo y tipos de rango.
- **Riesgo:** el profesor podría esperar MySQL. Se documenta en el README.

## ADR-002 — Java 21 y Spring Boot 4.0.x con springdoc 3.x
- **Contexto:** `demo-ddd` usa Java 25 y springdoc 2.8.x. El enunciado pide Java 21 como mínimo.
- **Decisión:** Java 21, Boot 4.0.x, springdoc 3.x (rama que corresponde a Boot 4, según la guía de Swagger).
- **Motivo:** springdoc 2.x corresponde a Boot 3. Una versión incompatible rompe el arranque.

## ADR-003 — Arquitectura hexagonal + DDD en Maven multi-módulo
- **Decisión:** `domain`, `application`, `infrastructure` (+ `frontend`). Dependencias `infrastructure → application → domain`.
- **Motivo:** el compilador impide que el dominio use Spring. Se verifica con ArchUnit.
- **Simplicidad:** sin CQRS completo, event sourcing ni microservicios.

## Desviaciones respecto a los repos del profesor (sección 0.3)

### Respecto a `demo-ddd`
| Debilidad | Corrección aplicada |
|-----------|---------------------|
| Contraseña de BD en `application-dev.yml`; nombre de BD copiado (`librarydb`) | Variables de entorno y `.env.example` |
| Flyway deshabilitado, `ddl-auto: create-drop`, migración que no coincide con la entidad | Flyway es la única fuente del esquema, `ddl-auto: validate` |
| `application-prod.yml` vacío | Perfil `prod` completo |
| Sin `.gitignore` (`target/`, `.vscode/` versionados) | `.gitignore` desde el primer commit |
| Validación de dominio mínima | Invariantes reales y excepciones de dominio |
| Unicidad sin consultar (`existsByCode` sin usar) | Validación en el caso de uso y `UNIQUE` en BD |
| Eventos de dominio sin despachar | Puerto `DomainEventPublisher` y `clearDomainEvents()` (a detallar al implementarlo) |
| `LocalDateTime.now()` en el dominio | Se inyecta `Clock` |
| Excepción de dominio en `common` | Cada excepción vive en su contexto |
| Sin `GlobalExceptionHandler` | Tomado de `security` |
| Sin manejo de transacciones | `@Transactional` en adaptadores y ejecutor transaccional en `BeansConfig` |
| `scanBasePackages` con código comentado | Configuración limpia |
| Borrado físico siempre | Baja lógica (`active`) donde tiene sentido |
| Java 25, springdoc 2.8.x | Java 21, springdoc 3.x |

### Respecto a `security`
| Debilidad | Corrección aplicada |
|-----------|---------------------|
| CORS con `*` fijo | Configurable por variable de entorno |
| Secreto JWT por defecto en el YAML | En `prod` solo por variable de entorno, sin valor por defecto |
| `ddl-auto: update`, H2 y MySQL | Solo PostgreSQL con Flyway |
| `@Service`, `@Transactional`, `@Value` en casos de uso | `application` pura |
| README inconsistente con el pom | README refleja la realidad |

> Las filas marcadas "a detallar" se completan con la decisión final de cada fase.

## Pendientes de decisión (se resuelven en la Fase 0)
- Nombre de la tabla de usuarios: `users` (el prompt dice `usuarios`, pero pide tablas en inglés).
- Teléfonos y direcciones: relación con `providers` y `clients`.
- Librería de PDF: OpenPDF propuesta.
- Almacenamiento del token en el frontend: access en memoria, refresh en `sessionStorage`.
- Gráficos del frontend: Chart.js incluido en el proyecto.
