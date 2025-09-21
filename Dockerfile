# -----------------------
# Stage 1: Build
# -----------------------
FROM maven:3.9.11-eclipse-temurin-24 AS build

WORKDIR /app

# Copiar pom y descargar dependencias para cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# -----------------------
# Stage 2: Runtime
# -----------------------
FROM eclipse-temurin:24-jdk-alpine

# Crear carpeta para la base de datos
RUN mkdir -p /usr/db

# Copiar JAR compilado desde build
COPY --from=build /app/target/sgved-0.0.1-SNAPSHOT.jar /usr/share/sgved.jar

# Copiar archivo de base de datos desde contexto Docker
COPY src/main/resources/data/bd_sgved* /usr/db/

# Arrancar la app pasando el path de la base de datos
ENTRYPOINT ["java", "-jar", "/usr/share/sgved.jar", "--DB_PATH=/usr/db/bd_sgved"]
