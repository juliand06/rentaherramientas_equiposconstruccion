# RentaTools — Plataforma de Renta de Herramientas y Equipos de Construcción

[![Java](https://img.shields.io/badge/Java-21%20LTS-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.8-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue.svg)](https://www.postgresql.org/)
[![Architecture](https://img.shields.io/badge/Architecture-Hexagonal%20%2B%20DDD-purple.svg)]()
[![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg)](LICENSE)

Plataforma empresarial de grado de producción para la gestión integral de alquiler de maquinaria pesada y herramientas de construcción. Implementada con **Arquitectura Hexagonal (Puertos y Adaptadores)**, principios de **Diseño Guiado por el Dominio (DDD)**, base de datos relacional normalizada en **4FN** con PostgreSQL 16, seguridad **JWT**, frontend interactivo de alta fidelidad estética y suite exhaustiva de pruebas automatizadas.

---

## 📑 Tabla de Contenidos

1. [Arquitectura y Principios de Diseño](#-arquitectura-y-principios-de-diseño)
2. [Estructura del Proyecto Maven Multi-Módulo](#-estructura-del-proyecto-maven-multi-módulo)
3. [Tecnologías y Stack Técnico](#-tecnologías-y-stack-técnico)
4. [Base de Datos y Normalización 4FN](#-base-de-datos-y-normalización-4fn)
5. [Frontend Industrial Interactivo](#-frontend-industrial-interactivo)
6. [Seguridad y Roles de Usuario](#-seguridad-y-roles-de-usuario)
7. [Endpoints de la API REST](#-endpoints-de-la-api-rest)
8. [Usuarios y Credenciales de Prueba](#-usuarios-y-credenciales-de-prueba)
9. [Guía Rápida de Ejecución](#-guía-rápida-de-ejecución)
10. [Pruebas Automatizadas y ArchUnit](#-pruebas-automatizadas-y-archunit)
11. [Documentación Complementaria](#-documentación-complementaria)

---

## 🏛 Arquitectura y Principios de Diseño

El sistema está construido siguiendo la **Arquitectura Hexagonal** y las tácticas de **Domain-Driven Design (DDD)**:

```
                  ┌───────────────────────────────────────────────┐
                  │                 INFRASTRUCTURE                │
                  │  (REST Controllers, JPA Entities, Security,   │
                  │   Flyway Migrations, Adapters, Nginx Static)  │
                  └───────────────────────┬───────────────────────┘
                                          │ depende de
                                          ▼
                  ┌───────────────────────────────────────────────┐
                  │                  APPLICATION                  │
                  │   (Use Cases, Commands, Ports, DTOs, Fakes)   │
                  │       *Java puro - Cero frameworks*           │
                  └───────────────────────┬───────────────────────┘
                                          │ depende de
                                          ▼
                  ┌───────────────────────────────────────────────┐
                  │                    DOMAIN                     │
                  │   (Aggregates, Value Objects, Domain Events,  │
                  │       Domain Exceptions, Repository Ports)    │
                  │       *Java puro - Lógica pura de negocio*    │
                  └───────────────────────────────────────────────┘
```

### Reglas de Arquitectura Inviolables

1. **Aislamiento absoluto:** `domain` y `application` son módulos en **Java puro**. No importan Spring, Hibernate, JPA ni Jackson.
2. **Dirección de dependencias:** $\text{infrastructure} \to \text{application} \to \text{domain}$. Esta regla es verificada y asegurada en tiempo de compilación por la suite de **ArchUnit** (`ArchitectureTest.java`).
3. **Casos de uso desacoplados:** Los casos de uso no usan anotaciones de Spring (`@Service`, `@Autowired`). Se instancian explícitamente como `@Bean` en clases de configuración técnica (`XxxBeansConfig`) en infraestructura.
4. **Dominio rico e invariantes:** Los agregados validan sus invariantes internas, calculan tarifas mediante Value Objects (`Money`, `DateRange`) y disparan eventos de dominio (`ReservationConfirmed`).

---

## 📦 Estructura del Proyecto Maven Multi-Módulo

```
rentaherramientas_equiposconstruccion/
├── pom.xml                               # Pom padre (BOM de dependencias, compilador Java 21)
├── Dockerfile                            # Multi-stage build (Maven Temurin 21 -> JRE 21)
├── docker-compose.yml                    # Orquestación de Postgres, Backend y Frontend
├── .env.example                          # Plantilla de variables de entorno seguras
│
├── domain/                               # Java 21 puro (Lógica de dominio)
│   ├── common/                           # AggregateRoot, DomainEvent, DomainException, Money
│   ├── identity/                         # Agregado User, UserId, Email, UserRepository
│   ├── catalog/                          # Agregado Tool, ToolId, ToolRepository
│   └── rental/                           # Agregado Reservation, ReservationId, Eventos
│
├── application/                          # Casos de uso de negocio (Java puro)
│   ├── identity/                         # RegisterUser, GetUserProfile, PasswordEncoderPort
│   ├── catalog/                          # RegisterTool, GetTool, ListTools, StoragePort
│   ├── rental/                           # CreateReservation, ConfirmReservation
│   ├── billing/                          # ProcessPayment, GetPayment, PdfGeneratorPort
│   └── reporting/                        # GetSystemMetricsUseCase, ReportingPort
│
├── infrastructure/                       # Adaptadores tecnológicos y frameworks
│   ├── common/                           # Security, GlobalExceptionHandler, OpenAPI
│   ├── identity/                         # UserController, UserJpaEntity, UserAdapter
│   ├── catalog/                          # ToolController, ToolJpaEntity, ToolAdapter
│   ├── rental/                           # ReservationController, ReservationJpaEntity
│   ├── billing/                          # PaymentController, PaymentJpaEntity, SimplePdf
│   ├── reporting/                        # ReportingController, ReportingAdapter (BI)
│   └── resources/
│       ├── application.yml               # Configuración base agnóstica
│       ├── application-dev.yml           # Perfil local con carga opcional de .env
│       ├── application-prod.yml          # Perfil estricto de producción
│       ├── db/migration/
│       │   ├── V1__schema.sql            # Esquema en 4FN (22 tablas, índices, GiST)
│       │   └── V2__seed_data.sql         # Seed con 20+ registros por entidad y BCrypt
│       └── static/                       # Recursos web estáticos embebidos
│
└── frontend/                             # Single Page Application servida por Nginx
    ├── nginx.conf                        # Reverse proxy hacia /api/ y /uploads/
    ├── Dockerfile                        # Imagen Nginx Alpine
    └── src/
        ├── index.html                    # Layout responsive con Bootstrap 5 y glassmorphism
        ├── styles.css                    # Sistema de diseño dark mode industrial
        ├── intro.js                      # Splash screen animado (cinemática 2.5D y Web Audio)
        └── app.js                        # Lógica reactiva SPA, JWT y roles
```

---

## 🛠 Tecnologías y Stack Técnico

- **Lenguaje:** Java 21 LTS (Records, Pattern Matching, Sequenced Collections).
- **Framework:** Spring Boot 4.0.8 (Web, Data JPA, Security, Actuator, Validation).
- **Documentación de API:** springdoc-openapi 3.1.1 (OpenAPI 3.0 / Swagger UI).
- **Base de Datos:** PostgreSQL 16 con extensión `btree_gist` para exclusión de solapamientos.
- **Control de Migraciones:** Flyway Community Edition (`V1__schema.sql` y `V2__seed_data.sql`).
- **Seguridad:** Spring Security 6.x + JJWT 0.12.7 (Access Token 15 min, Refresh Token 7 días).
- **Pruebas:** JUnit 5, MockMvc, ArchUnit 1.4.1 y Fakes en memoria.
- **Frontend:** JavaScript Vanilla (ES Modules), HTML5 semántico, CSS3, Bootstrap 5, Web Audio API, Canvas 2D.
- **Contenedores:** Docker, Docker Compose, Nginx 1.27 Alpine.

---

## 🗄 Base de Datos y Normalización 4FN

El esquema de base de datos cumple rigurosamente con la **Cuarta Forma Normal (4FN)**, eliminando dependencias multivaluadas y redundancias:

- **22 tablas normalizadas:**
  - `roles`, `users`, `user_roles`, `user_phones`, `user_addresses`.
  - `providers`, `clients`, `categories`, `tools`, `tool_specifications`, `tool_images`, `tool_maintenance_logs`.
  - `reservations`, `reservation_status_history`, `payments`, `invoices`, `invoice_items`.
  - `tool_deliveries`, `tool_returns`, `tool_damages`, `damage_photos`, `audit_logs`.
- **Prevención de solapamiento a nivel de motor:**
  PostgreSQL bloquea colisiones de reserva concurrentes con:
  ```sql
  CONSTRAINT check_no_overlapping_reservations
    EXCLUDE USING gist (
      tool_id WITH =,
      daterange(start_date, end_date, '[]') WITH &&
    ) WHERE (is_active = TRUE)
  ```
- **Datos iniciales (Seed):** Más de 20 registros por entidad principal, contraseñas hasheadas con BCrypt e inventario realista para Colombia en moneda nacional (COP).
- **Documentación de BD:** En [docs/database/](docs/database/) encontrará:
  - `diccionario-datos.md`: Diccionario técnico completo con descripción campo por campo.
  - `diagrama-er.svg`: Diagrama Entidad-Relación visual en alta resolución.
  - `schema-drawsql.sql`: Script SQL compatible con la herramienta DrawSQL.
  - `schema.dbml`: Esquema en formato Database Markup Language.

---

## 🚜 Frontend Industrial Interactivo

El frontend ofrece una experiencia de usuario moderna y estilizada:

1. **Splash Screen Cinemático:**
   - Artefacto cinemático 2.5D de una excavadora hidráulica reactiva a las acciones del usuario.
   - Partículas ambientales renderizadas en HTML5 Canvas a 60 fps.
   - Efectos sonoros mecánicos sintetizados en tiempo real mediante **Web Audio API** (sin librerías de audio externas).
   - Animación de compuertas blindadas industriales (*blast doors*).
2. **Catálogo de Maquinaria en COP:**
   - Filtrado reactivo por categorías pesadas (Excavación, Elevación, Concreto, etc.).
   - Buscador en tiempo real y cálculo automático de depósitos de garantía.
3. **Modal de Reserva Dinámico:**
   - Selector de fechas con cálculo instantáneo de días hábiles y valor total.
4. **Paneles Especializados por Rol:**
   - **Cliente:** Centro de control con *stepper* de 4 fases (`Solicitada` $\to$ `Confirmada/Paga` $\to$ `En Obra` $\to$ `Devuelta`), pasarela de pago simulada y visor de contratos digitales.
   - **Proveedor:** Registro de nueva maquinaria pesada y control de flota.
   - **Administrador:** Tablero de Business Intelligence con KPIs globales, gráfico de barras de ingresos por categoría y gráfico circular (donut) de estado de flota.
5. **Zonas de Proyecto & Logística de Despacho (Santander):**
   - Mapa interactivo vectorial SVG animado de frentes de obra en el departamento de Santander y el Área Metropolitana de Bucaramanga (AMB).
   - Visualización de hubs operativos (Patio Central Girón Chimitá, Bucaramanga, Floridablanca, Piedecuesta, Barrancabermeja y Lebrija).
   - Monitoreo logístico con camabajas animadas en ruta, barrido holográfico a 60fps y fichas de frentes de obra autónomas sin depender de APIs de terceros ni tokens.
6. **Facturación Electrónica DIAN y Pasarela Colombiana:**
   - Factura electrónica oficial con NIT, resolución DIAN, código CUFE de 96 caracteres, código QR y estilos optimizados para impresión/PDF.
   - Pasarela de pago simulada para contratistas con soporte para Tarjetas, PSE (Débito Bancario) y Nequi/Daviplata.
   - Ciclo de vida completo: Pago, cancelación de reservas (con liberación de flota) y devolución de maquinaria con liberación de garantía.

---

## 🔒 Seguridad y Roles de Usuario

El sistema implementa autenticación basada en **JWT (JSON Web Tokens)**:
- **Stateless:** Las sesiones no se almacenan en memoria del servidor.
- **Acceso granular:** Protegido mediante `@EnableMethodSecurity` y reglas en `SecurityConfig`.
- **Roles:**
  - `ADMIN`: Gestión de usuarios, métricas globales, supervisión de inventario y auditoría.
  - `PROVIDER`: Publicación de herramientas y seguimiento de flota.
  - `CLIENT`: Exploración de catálogo, creación de reservas y pago de alquileres.

---

## 📡 Endpoints de la API REST

| Contexto | Método | Endpoint | Rol requerido | Descripción |
|---|---|---|---|---|
| **Auth** | `POST` | `/api/auth/login` | Público | Autenticación y obtención de Access + Refresh Tokens |
| **Auth** | `POST` | `/api/auth/refresh` | Público | Rotación y renovación de tokens JWT |
| **Usuarios** | `POST` | `/api/users` | Público | Registro de nuevos usuarios con rol |
| **Usuarios** | `GET` | `/api/users/{id}` | Autenticado | Consulta de perfil de usuario |
| **Catálogo** | `GET` | `/api/tools` | Público | Listado de herramientas con filtro opcional de categoría |
| **Catálogo** | `GET` | `/api/tools/{id}` | Público | Detalle de herramienta por ID |
| **Catálogo** | `POST` | `/api/tools` | PROVIDER / ADMIN | Registro de nueva maquinaria pesada |
| **Reservas** | `POST` | `/api/reservations` | CLIENT / ADMIN | Creación de reserva en estado `PENDING` |
| **Reservas** | `GET` | `/api/reservations/{id}` | Autenticado | Consulta de detalle de reserva |
| **Reservas** | `PUT` | `/api/reservations/{id}/confirm` | Autenticado | Confirmación de reserva y disparo de evento |
| **Pagos** | `POST` | `/api/payments` | CLIENT / ADMIN | Procesamiento de pago simulado y emisión de recibo |
| **Pagos** | `GET` | `/api/payments/{id}` | Autenticado | Consulta de comprobante de pago |
| **Reportes** | `GET` | `/api/reports/metrics` | ADMIN | Métricas consolidadas del sistema (KPIs de BI) |
| **Salud** | `GET` | `/actuator/health` | Público | Healthcheck del sistema |

> **Documentación interactiva Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
> **Especificación OpenAPI 3.0:** Disponible en [`docs/api/openapi.json`](docs/api/openapi.json) y [`docs/api/openapi.yaml`](docs/api/openapi.yaml).

---

## 👥 Usuarios y Credenciales de Prueba

Todos los usuarios fueron precargados en la migración `V2__seed_data.sql`:

| Rol | Correo Electrónico | Contraseña |
|---|---|---|
| **Administrador** | `admin@rentatools.com` | `Admin123456!` |
| **Proveedor** | `proveedor.equipos@maquinariacolombia.com` | `Prov123456!` |
| **Cliente** | `cliente.constructora@constructora.com` | `Client123456!` |

---

## 🚀 Guía Rápida de Ejecución

### Opción A — Ejecución con Docker Compose (Recomendada)

1. Clone el repositorio e ingrese al directorio:
   ```bash
   git clone https://github.com/juliand06/rentaherramientas_equiposconstruccion.git
   cd rentaherramientas_equiposconstruccion
   ```
2. Cree el archivo de variables `.env`:
   ```bash
   cp .env.example .env
   ```
3. Levante los contenedores:
   ```bash
   docker compose up -d --build
   ```
4. Acceda a la plataforma:
   - **Frontend Web:** [http://localhost:8081](http://localhost:8081)
   - **Backend API & Swagger:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Opción B — Ejecución Local con Maven y PostgreSQL

1. Asegúrese de tener PostgreSQL 16 corriendo en el puerto 5432 con una base de datos llamada `rentatools`.
2. Configure `.env` con sus credenciales de base de datos.
3. Ejecute la aplicación:
   ```bash
   mvn -pl infrastructure spring-boot:run
   ```
4. Abra su navegador en [http://localhost:8080](http://localhost:8080) (la app sirve los recursos estáticos automáticamente).

---

## 🧪 Pruebas Automatizadas y ArchUnit

La suite de pruebas automatizadas garantiza la estabilidad del sistema:

- **ArchUnit (`ArchitectureTest.java`):**
  - Regla estricta `infrastructure -> application -> domain`.
  - Cero dependencias de frameworks en el dominio y la aplicación.
  - Verificación de inmutabilidad en Value Objects.
- **Pruebas de Dominio y Value Objects:**
  - Operaciones monetarias con `Money` y precisión `BigDecimal`.
  - Invariantes de fechas con `DateRange`.
  - Máquina de estados de `Reservation` y eventos de dominio.
- **Pruebas de Casos de Uso (Application):**
  - Fakes en memoria que simulan repositorios sin requerir base de datos ni mocks lentos.
- **Pruebas de Integración (MockMvc):**
  - Ciclo completo de vida de alquiler: creación de herramienta $\to$ reserva $\to$ confirmación $\to$ pago $\to$ métricas de BI (`CompleteRentalLifecycleIntegrationTest.java`).
  - Autenticación JWT, credenciales inválidas y rotación de tokens (`AuthIntegrationTest.java`).
  - Manejo global de excepciones (`GlobalExceptionHandlerTest.java`).

Para ejecutar todas las pruebas:
```bash
mvn clean test
```

---

## 📚 Documentación Complementaria

- [Guía de Compilación](docs/COMPILACION.md) — Requisitos, comandos Maven, empaquetado JAR/WAR y resolución de incidencias.
- [Guía de Despliegue](docs/DESPLIEGUE.md) — Configuración para Docker Compose, Tomcat 10+ y servicios systemd.
- [Diccionario de Datos](docs/database/diccionario-datos.md) — Detalle de las 22 tablas en 4FN y tipos de datos.
- [Diagrama Entidad-Relación](docs/database/diagrama-er.svg) — Modelo relacional completo en SVG.
- [Colección Postman](docs/postman/) — Colección de requests y ambiente preconfigurado para pruebas de API.
- [Especificación OpenAPI (Swagger)](docs/api/) — Contratos REST en formato JSON y YAML.

