# DESPLIEGUE.md — Guía de despliegue en producción y entornos locales

Esta guía detalla las alternativas disponibles para desplegar la **Plataforma de Renta de Herramientas y Equipos de Construcción (RentaTools)**.

---

## 1. Métodos de despliegue soportados

| Método | Tipo | Entorno recomendado | Ventajas |
|---|---|---|---|
| **Docker Compose** | Contenedores orquestados | Producción / Pruebas / CI-CD | Aislamiento total, configuración zero-config, proxy Nginx integrado |
| **Apache Tomcat** | Despliegue WAR tradicional | Servidores legados o institucionales | Compatible con servidores de aplicaciones Jakarta EE 10 / Tomcat 10+ |
| **Ejecución standalone** | Systemd / Bare Metal | Máquinas virtuales dedicadas | Rendimiento nativo sin capa de virtualización |

---

## 2. Despliegue principal: Docker Compose

Docker Compose es la estrategia primaria y recomendada. Orquesta tres contenedores interconectados mediante una red interna:

```
[ Cliente Web / Navegador ]
             │
             ▼ Puerto 8081 (o 80)
   ┌───────────────────┐
   │  Nginx Frontend   │ ── Archivos estáticos SPA (HTML5, CSS3, JS Vanilla)
   │  (Reverse Proxy)  │ ── Proxy a /api/ y /uploads/ hacia el backend
   └─────────┬─────────┘
             │ Red interna Docker
             ▼ Puerto 8080
   ┌───────────────────┐
   │  Backend App      │ ── Spring Boot 4.x + Java 21 JRE (Multi-stage build)
   │  (Spring Boot)    │ ── Migraciones Flyway ejecutadas al iniciar
   └─────────┬─────────┘
             │ Red interna Docker
             ▼ Puerto 5432
   ┌───────────────────┐
   │  PostgreSQL 16    │ ── Base de datos con soporte GiST (EXCLUDE constraint)
   │  (Motor de BD)    │ ── Volumen persistente 'pgdata'
   └───────────────────┘
```

### 2.1. Preparación de variables de entorno

Cree un archivo `.env` en la raíz del proyecto configurado para producción:

```properties
SPRING_PROFILES_ACTIVE=prod

# Base de datos
DB_NAME=rentatools
DB_USER=rentatools_user
DB_PASSWORD=SuperSecurePassword2026!
POSTGRES_PORT=5432

# Seguridad JWT (genere un secreto de 64 bytes: openssl rand -base64 48)
JWT_SECRET=c29tZS1zdXBlci1zZWNyZXQta2V5LWZvci1wcm9kdWN0aW9uLXJlbnRhdG9vbHMtMjAyNi1qd3Q=
JWT_ACCESS_MINUTES=15
JWT_REFRESH_DAYS=7

# Orígenes CORS permitidos
CORS_ALLOWED_ORIGINS=http://localhost:8081,https://midominio.com

# Puertos expuestos en el servidor host
APP_PORT=8080
FRONTEND_PORT=8081
```

### 2.2. Inicio de los servicios

Ejecute la construcción de imágenes y el inicio en segundo plano:

```bash
docker compose up -d --build
```

### 2.3. Verificación del estado de los contenedores

Compruebe que todos los contenedores reporten estado `healthy`:

```bash
docker compose ps
```

Salida esperada:
```
NAME                  IMAGE                  COMMAND                  SERVICE      STATUS
rentatools-postgres   postgres:16            "docker-entrypoint.s…"   postgres     Up (healthy)
rentatools-app        rentatools-app         "sh -c 'exec java $J…"   app          Up (healthy)
rentatools-frontend   rentatools-frontend    "/docker-entrypoint.…"   frontend     Up (healthy)
```

### 2.4. Comandos útiles de operación

- **Ver logs en tiempo real de todos los servicios:**
  ```bash
  docker compose logs -f
  ```
- **Ver logs exclusivos del backend:**
  ```bash
  docker compose logs -f app
  ```
- **Reiniciar un contenedor específico:**
  ```bash
  docker compose restart app
  ```
- **Detener todos los servicios:**
  ```bash
  docker compose down
  ```
- **Detener y purgar volúmenes (¡destruye la base de datos!):**
  ```bash
  docker compose down -v
  ```

---

## 3. Despliegue alternativo: Apache Tomcat 10+ (WAR)

Para desplegar en un servidor corporativo Tomcat:

### 3.1. Requisitos de Tomcat
- **Apache Tomcat 10.1.x o superior** (compatible con Servlet 6.0 y Jakarta EE 10).
- JDK 21 instalado en el servidor.

### 3.2. Pasos para generar y desplegar el WAR

1. En `infrastructure/pom.xml`, configure el empaquetado como war:
   ```xml
   <packaging>war</packaging>
   ```
2. Añada la dependencia embebida de Tomcat con scope `provided`:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-tomcat</artifactId>
       <scope>provided</scope>
   </dependency>
   ```
3. Genere el paquete:
   ```bash
   mvn clean package -DskipTests
   ```
4. Copie el archivo `infrastructure/target/rentatools.war` al directorio `webapps/` de Apache Tomcat:
   ```bash
   cp infrastructure/target/rentatools.war /opt/tomcat/webapps/
   ```
5. Configure las variables de entorno en `/opt/tomcat/bin/setenv.sh`:
   ```bash
   export DB_HOST="localhost"
   export DB_PORT="5432"
   export DB_NAME="rentatools"
   export DB_USER="rentatools"
   export DB_PASSWORD="rentapassword"
   export JWT_SECRET="clave-secreta-jwt-de-mas-de-32-caracteres"
   export SPRING_PROFILES_ACTIVE="prod"
   ```
6. Inicie Tomcat:
   ```bash
   /opt/tomcat/bin/startup.sh
   ```

---

## 4. Despliegue como servicio Systemd (Linux Bare Metal / VM)

Si prefiere ejecutar directamente el JAR en una instancia Linux sin Docker:

1. Cree el usuario del sistema:
   ```bash
   sudo useradd -r -s /bin/false rentatools
   ```
2. Copie el archivo `rentatools.jar` a `/opt/rentatools/rentatools.jar`.
3. Cree el directorio de subidas:
   ```bash
   sudo mkdir -p /var/rentatools/uploads
   sudo chown -R rentatools:rentatools /opt/rentatools /var/rentatools
   ```
4. Cree el archivo `/etc/systemd/system/rentatools.service`:
   ```ini
   [Unit]
   Description=RentaTools Heavy Equipment Platform
   After=syslog.target network.target postgresql.service

   [Service]
   Type=simple
   User=rentatools
   Group=rentatools
   WorkingDirectory=/opt/rentatools
   EnvironmentFile=/opt/rentatools/.env
   ExecStart=/usr/bin/java -Xms512m -Xmx2048m -jar /opt/rentatools/rentatools.jar
   Restart=always
   RestartSec=10

   [Install]
   WantedBy=multi-user.target
   ```
5. Recargue e inicie el servicio:
   ```bash
   sudo systemctl daemon-reload
   sudo systemctl enable --now rentatools
   sudo systemctl status rentatools
   ```

---

## 5. Respaldos y Restauración de Base de Datos

### 5.1. Generar respaldo completo con pg_dump

```bash
docker compose exec postgres pg_dump -U rentatools -d rentatools -F c -b -v -f /tmp/backup_rentatools.dump
docker cp rentatools-postgres:/tmp/backup_rentatools.dump ./backup_rentatools.dump
```

### 5.2. Restaurar respaldo

```bash
docker cp ./backup_rentatools.dump rentatools-postgres:/tmp/backup_rentatools.dump
docker compose exec postgres pg_restore -U rentatools -d rentatools -v /tmp/backup_rentatools.dump
```

---

## 6. Monitoreo y Salud del Sistema

La plataforma expone el endpoint estándar de salud:

```bash
curl -fsS http://localhost:8080/actuator/health
```

Respuesta JSON:
```json
{
  "status": "UP"
}
```
