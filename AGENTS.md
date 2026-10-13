# AGENTS.md — Convenciones del proyecto

Plataforma de Renta de Herramientas y Equipos de Construcción.
Este archivo es la guía de convenciones. Se sigue en todas las fases.

## 1. Stack fijo

| Tema | Decisión |
|------|----------|
| Lenguaje | Java 21 |
| Framework | Spring Boot 4.0.x |
| Documentación API | springdoc-openapi 3.x |
| Base de datos | PostgreSQL 16, esquema solo por Flyway |
| Seguridad | Spring Security + JWT (jjwt 0.12.x), roles `ADMIN`, `PROVIDER`, `CLIENT` |
| Frontend | HTML + CSS + JS vanilla (ES modules) + Bootstrap 5 |
| Pruebas | JUnit 5, fakes en memoria, MockMvc, Testcontainers |
| Despliegue | Docker Compose (principal), WAR en Tomcat (alternativo) |

## 2. Módulos Maven

```
ejerciciodemo/
├── pom.xml                 (padre, packaging pom, importa spring-boot-dependencies)
├── domain/                 Java puro. Solo JUnit en test.
├── application/            Java puro. Depende solo de domain.
├── infrastructure/         Spring, JPA, REST, seguridad. Depende de application.
└── frontend/               Archivos estáticos servidos por nginx.
```

### Regla de dependencias (inviolable)

`infrastructure → application → domain`

- `domain` no importa nada de Spring, JPA ni Jackson.
- `application` no importa Spring. Sus casos de uso no llevan anotaciones.
- Esta regla se verifica con una prueba ArchUnit.

## 3. Paquetes

Paquete raíz: `com.rentatools`.

Bounded contexts: `identity`, `catalog`, `rental`, `billing`, `reporting`.
Cada módulo repite los contextos como subpaquetes.

```
domain/<contexto>/
  model/aggregate/    agregados (constructor privado, factories create/restore)
  model/valueobject/  value objects e IDs (records)
  event/              eventos de dominio (records)
  exception/          excepciones de ESE contexto
  port/repository/    puertos de salida
domain/common/        AggregateRoot, DomainEvent, DomainException

application/<contexto>/
  usecase/  command/  dto/  exception/  port/
application/common/exception/ApplicationException

infrastructure/<contexto>/
  adapters/in/rest/controllers/   adapters/in/rest/dtos/
  adapters/out/persistence/entity|repositories|mappers/
  config/   XxxBeansConfig
infrastructure/common/            GlobalExceptionHandler, ApiErrorResponse, seguridad
```

## 4. Nombres

- Código, tablas y columnas en **inglés**. Tablas en plural y `snake_case`.
- Documentación, Javadoc, README y mensajes de error al usuario en **español**.
- Entidad JPA: `XxxJpaEntity`. Repositorio: `XxxJpaRepository`. Adaptador: `XxxRepositoryAdapter`. Mapper: `XxxPersistenceMapper`.
- Casos de uso: verbo + objeto, con método `execute(...)`. Ej.: `RegisterToolUseCase`.
- Comandos y DTOs: `records`.
- IDs: `record XxxId(UUID value)` con `generate()`.

## 5. Reglas de código

- Controladores: solo traducen request → command, llaman al caso de uso y devuelven la respuesta. Cero lógica de negocio.
- Agregados con invariantes reales. Lanzan excepciones de dominio con sentido.
- Nada de `LocalDateTime.now()` en el dominio: se inyecta `Clock` o se recibe la fecha.
- Transacciones solo en infraestructura (`@Transactional` en adaptadores o un decorador con `TransactionTemplate`).
- Lombok solo en infraestructura y solo si simplifica. Nunca en `domain`.
- Mappers manuales para persistencia. MapStruct solo si reduce boilerplate REST.
- Sin credenciales en el código. Todo por variables de entorno (`${VAR:default}` solo en `dev`).
- `ddl-auto: validate`. Las migraciones deben coincidir exactamente con las entidades.
- Prefiere código legible y convencional. Comenta solo el POR QUÉ.
- Simplicidad: sin CQRS completo, event sourcing ni microservicios.

## 6. Comentarios y Javadoc

- Javadoc breve en español en clases clave.
- Si una clase aplica un concepto de las guías del profesor, el Javadoc dice cuál y por qué.
- Cada concepto aplicado se registra en `docs/MATRIZ_CUMPLIMIENTO.md` (Tabla B).

## 7. Git y commits

- Rama `main`. Identidad configurada en el repo (no global).
- **Conventional Commits** en español, al cierre de cada fase o bloque lógico:
  `feat:`, `fix:`, `docs:`, `test:`, `chore:`, `refactor:`, `build:`.
- Commits pequeños y frecuentes. El `.gitignore` ya existe desde el primer commit.
- Nunca se versiona `target/`, `.env`, `.vscode/`, `uploads/`.

## 8. Cómo correr pruebas

```bash
mvn clean verify                      # todo: compila, pruebas y verificación
mvn -pl domain test                   # solo dominio
mvn -pl application -am test          # casos de uso (con dominio)
mvn -pl infrastructure -am verify     # incluye integración (requiere Docker)
```

- Dominio y casos de uso: sin Docker.
- Integración de persistencia: Testcontainers (PostgreSQL), requiere Docker.
- Controladores: MockMvc.
- Flujo completo: reserva → pago → factura → devolución.

## 9. Documentación del proyecto

| Archivo | Contenido |
|---------|-----------|
| `README.md` | Descripción, arquitectura, ejecución, endpoints, usuarios de prueba |
| `docs/COMPILACION.md` | Requisitos, Maven, JAR/WAR, problemas comunes |
| `docs/DESPLIEGUE.md` | Docker Compose y Tomcat |
| `docs/DECISIONES.md` | ADRs y desviaciones respecto a los repos del profesor |
| `docs/APRENDIZAJE.md` | Conceptos, preguntas de repaso y errores evitados |
| `docs/MATRIZ_CUMPLIMIENTO.md` | Requisitos (Tabla A) y conceptos aplicados (Tabla B) |
| `docs/database/` | Esquema para drawSQL, DBML, ER, diccionario |
| `docs/api/` | `openapi.json` y `openapi.yaml` |
| `docs/postman/` | Colección con flujos completos |

Si cambia el esquema o la API, se actualizan estos archivos en el mismo commit.
