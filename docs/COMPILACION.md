# COMPILACION.md — Guía de compilación y empaquetado

Esta guía documenta los requisitos, pasos y comandos necesarios para compilar, verificar y empaquetar el proyecto **RentaTools** (Plataforma de Renta de Herramientas y Equipos de Construcción).

---

## 1. Requisitos previos del sistema

| Herramienta | Versión mínima | Versión recomendada | Comprobación |
|---|---|---|---|
| **Java (JDK)** | 21 (LTS) | 21.0.x (Eclipse Temurin / OpenJDK) | `java -version` |
| **Apache Maven** | 3.8.x | 3.9.x | `mvn -version` |
| **Git** | 2.30+ | Última versión estable | `git --version` |
| **Docker Engine & Compose** | 24.0+ | Docker Desktop / Docker Engine 26+ | `docker --version && docker compose version` |

> [!IMPORTANT]
> El proyecto requiere **Java 21**. No compila en versiones inferiores (Java 8, 11 o 17) debido al uso de *records* avanzados, *pattern matching*, *sequenced collections* y las dependencias de Spring Boot 4.0.x.

---

## 2. Estructura de módulos Maven

El proyecto está diseñado bajo una arquitectura modular limpia orientada al dominio (DDD + Arquitectura Hexagonal):

```
rentaherramientas_equiposconstruccion/
├── pom.xml                 # Reactor raíz (BOM y dependencyManagement)
├── domain/                 # Entidades y lógica pura de negocio (cero dependencias externas)
├── application/            # Casos de uso, puertos e interfaces (depende solo de domain)
├── infrastructure/         # Adaptadores REST, JPA, Spring Security, JWT, Flyway
└── frontend/               # Single Page Application estática servida por Nginx
```

### Regla de dependencias inviolable

$$\text{infrastructure} \longrightarrow \text{application} \longrightarrow \text{domain}$$

- `domain` no contiene anotaciones de Spring, JPA, Hibernate ni Jackson.
- `application` no contiene anotaciones `@Service`, `@Autowired` ni `@Component`.
- La regla se valida automáticamente en cada build mediante **ArchUnit** en `ArchitectureTest.java`.

---

## 3. Configuración del entorno local

Antes de compilar o ejecutar en modo desarrollo, cree el archivo de variables `.env` a partir de la plantilla:

```bash
cp .env.example .env
```

Parámetros clave en `.env`:

```properties
SPRING_PROFILES_ACTIVE=dev
DB_HOST=localhost
DB_PORT=5432
DB_NAME=rentatools
DB_USER=rentatools
DB_PASSWORD=rentapass123
JWT_SECRET=supersecretkeyforrentatoolsappdevelopment20261014
JWT_ACCESS_MINUTES=15
JWT_REFRESH_DAYS=7
CORS_ALLOWED_ORIGINS=http://localhost:8081
SERVER_PORT=8080
```

---

## 4. Comandos de compilación y pruebas

### 4.1. Compilación del código fuente

Para compilar todos los submódulos sin ejecutar pruebas:

```bash
mvn clean compile
```

### 4.2. Ejecución completa del ciclo de pruebas y verificación

Compila, ejecuta las pruebas unitarias, pruebas de arquitectura (ArchUnit) y pruebas de integración MockMvc:

```bash
mvn clean test
```

O para el ciclo de vida completo con validación y empaquetado:

```bash
mvn clean verify
```

### 4.3. Pruebas por módulo independiente

- **Solo capa de dominio:**
  ```bash
  mvn -pl domain test
  ```
- **Capa de aplicación y casos de uso:**
  ```bash
  mvn -pl application -am test
  ```
- **Capa de infraestructura y controladores REST (MockMvc + ArchUnit):**
  ```bash
  mvn -pl infrastructure -am test
  ```

---

## 5. Empaquetado de artefactos

### 5.1. JAR ejecutable (Spring Boot Fat JAR)

Para generar el JAR autocontenido listo para producción o contenedores Docker:

```bash
mvn clean package -DskipTests
```

El artefacto resultante se ubica en:
```
infrastructure/target/rentatools.jar
```

Para ejecutar directamente el JAR empaquetado:
```bash
java -jar infrastructure/target/rentatools.jar
```

### 5.2. Soporte para despliegue en servidor tradicional (WAR en Tomcat)

La clase principal `RentaToolsApplication` extiende `SpringBootServletInitializer`:

```java
@SpringBootApplication
public class RentaToolsApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RentaToolsApplication.class);
    }
}
```

Para empaquetar como WAR:
1. Cambiar temporalmente `<packaging>jar</packaging>` a `<packaging>war</packaging>` en `infrastructure/pom.xml`.
2. Marcar `spring-boot-starter-tomcat` con `<scope>provided</scope>`.
3. Ejecutar `mvn clean package -DskipTests`.

---

## 6. Inspección del árbol de dependencias (`mvn dependency:tree`)

El comando `mvn dependency:tree` permite verificar que no existan dependencias duplicadas ni conflictos entre versiones transitivas:

```bash
mvn dependency:tree
```

### Resumen del reactor:

- **`domain`**:
  - `org.junit.jupiter:junit-jupiter` (scope: test)
- **`application`**:
  - `com.rentatools:domain:1.0.0-SNAPSHOT`
  - `org.junit.jupiter:junit-jupiter` (scope: test)
- **`infrastructure`**:
  - `com.rentatools:application:1.0.0-SNAPSHOT`
  - `org.springframework.boot:spring-boot-starter-web`
  - `org.springframework.boot:spring-boot-starter-data-jpa`
  - `org.springframework.boot:spring-boot-starter-security`
  - `org.springframework.boot:spring-boot-starter-validation`
  - `org.springframework.boot:spring-boot-starter-actuator`
  - `org.postgresql:postgresql` (scope: runtime)
  - `org.flywaydb:flyway-core` + `flyway-database-postgresql`
  - `org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.1`
  - `io.jsonwebtoken:jjwt-api:0.12.7`
  - `com.tngtech.archunit:archunit:1.4.1` (scope: test)

---

## 7. Solución de problemas comunes

| Problema | Causa probable | Solución |
|---|---|---|
| `Unsupported class file major version 65` | Se está compilando con un JDK inferior a 21 | Instale JDK 21 y configure `JAVA_HOME` apuntando al JDK 21 |
| `Failed to load ApplicationContext` en tests | Base de datos PostgreSQL no disponible o credenciales erróneas | Revise variables de entorno o active el perfil dev con `.env` configurado |
| `ArchUnit: Architecture violation` | Un módulo de menor nivel importó una clase prohibida | Verifique que `domain` y `application` no importen paquetes de Spring o Jackson |
| `Port 8080 already in use` | Otra aplicación está escuchando en el puerto 8080 | Cambie `SERVER_PORT` en `.env` o detenga el proceso que ocupa el puerto |
| `Cannot resolve symbol jakarta.*` | Dependencias desactualizadas o no descargadas | Ejecute `mvn clean install -U` para forzar la actualización del repositorio local |
