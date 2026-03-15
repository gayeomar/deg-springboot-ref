# Multi-stage build for Hello World Servlet on Open Liberty

# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn clean package

# Stage 2: Deploy to Open Liberty
FROM icr.io/appcafe/open-liberty:full-java17-openj9-ubi

# Copy Liberty configuration
COPY --chown=1001:0 src/main/liberty/config/server.xml /config/server.xml

# Copy the WAR file from builder stage
COPY --chown=1001:0 --from=builder /app/target/hello-world-servlet.war /config/apps/

# Set default ports
ENV HTTP_PORT=9080
ENV HTTPS_PORT=9443

# Expose ports
EXPOSE 9080 9443

# Configure Liberty
RUN configure.sh
