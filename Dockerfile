# syntax=docker/dockerfile:1

# ---------- Etapa 1: compilación ----------
# Maven + JDK 21 solo existen en esta etapa; no llegan a la imagen final.
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Primero solo los pom: Docker reutiliza la caché de dependencias si no cambian.
COPY pom.xml .
COPY domain/pom.xml domain/pom.xml
COPY application/pom.xml application/pom.xml
COPY infrastructure/pom.xml infrastructure/pom.xml
COPY frontend/pom.xml frontend/pom.xml
RUN mvn -B -ntp -q dependency:go-offline -pl domain,application,infrastructure -am || true

COPY domain domain
COPY application application
COPY infrastructure infrastructure
RUN mvn -B -ntp -q -pl infrastructure -am -DskipTests package

# ---------- Etapa 2: ejecución ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# curl solo para el healthcheck del contenedor.
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/* \
    && groupadd --system app \
    && useradd --system --gid app --home /app app \
    && mkdir -p /data/uploads \
    && chown -R app:app /app /data

COPY --from=build /workspace/infrastructure/target/rentatools.jar app.jar

# Se ejecuta sin privilegios de root.
USER app
ENV UPLOADS_DIR=/data/uploads \
    JAVA_OPTS="-XX:MaxRAMPercentage=75"
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
