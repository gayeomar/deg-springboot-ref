# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Overview

This is a multi-module reference project demonstrating Java web technologies across two independent sub-projects:

- `01_hello-world/` — Maven-based Jakarta EE servlet running on Open Liberty
- `02_tracing/` — Gradle multi-project with 3 Spring Boot microservices demonstrating distributed tracing

## Build Commands

### 01_hello-world (Maven + Open Liberty)

```bash
cd 01_hello-world

mvn clean package           # Build WAR
mvn liberty:dev             # Start in dev mode (hot reload) at http://localhost:9080/hello
mvn liberty:run             # Start server (foreground)
mvn liberty:start           # Start server (background)
mvn liberty:stop            # Stop background server

docker build -t hello-world-servlet:latest .    # Build container image
docker run -p 9080:9080 hello-world-servlet     # Run container
```

### 02_tracing (Gradle + Spring Boot)

Run from `02_tracing/` directory:

```bash
./gradlew :service-01:build    # Build a single service
./gradlew :service-01:test     # Test a single service
./gradlew :service-01:bootRun  # Run a single service

scripts/run.sh         # Start all 3 services sequentially
scripts/test-build.sh  # Build all 3 services and clean artifacts
```

Services run on: service-01 → 8080, service-02 → 8081, service-03 → 8082

Spring Actuator endpoints are at `/manage/**` (not the default `/actuator`).

## Architecture

### 01_hello-world

Simple `HttpServlet` subclass using `@WebServlet` annotation (no web.xml). Open Liberty is configured via `src/main/liberty/config/server.xml` with features `servlet-6.0` and `jsp-3.1`. Packaged as WAR.

### 02_tracing — Microservice Chain

Three services form a call chain to demonstrate distributed tracing:

```
service-01 (8080) → service-02 (8081) → service-03 (8082)
```

Service-to-service calls use Spring WebFlux `WebClient` (reactive). All services expose REST APIs and actuator health/metrics.

**Key architectural differences between services:**
- service-01 and service-02 use **Micrometer Tracing + Brave** (Zipkin B3 propagation)
- service-03 uses **Micrometer Tracing + OpenTelemetry** bridge with `opentelemetry-exporter-zipkin`
- All services use **Log4j2** (Spring's default Logback is excluded via `implementation.exclude`)
- All services enable **virtual threads** (`spring.threads.virtual.enabled=true`)
- Configured for **Spring Cloud 2023.0.2** dependency management

### Deployment

Each service in `02_tracing` includes deployment manifests in `deploy/`:
- `deploy/k8s/` — Kubernetes Deployment, Service, Route manifests
- `deploy/kustomize/` — Base + overlays (dev) for multi-environment configs
- `deploy/openshift-knative/` — Knative serverless configs (service-01, service-02 only)
- `deploy/argocd/` — ArgoCD GitOps app config (service-03 only)

CI/CD via Tekton pipelines is in `cicd-tekton/` for service-02 and service-03.

## Tracing Configuration

Set `management.zipkin.tracing.endpoint` in `application.properties` to point to a Zipkin or OTEL collector. For SigNoz cloud:

```bash
OTEL_RESOURCE_ATTRIBUTES=service.name=springboot-reference \
OTEL_EXPORTER_OTLP_HEADERS="signoz-access-token=<token>" \
OTEL_EXPORTER_OTLP_ENDPOINT=https://ingest.us.signoz.cloud:443 \
java -javaagent:<path>/opentelemetry-javaagent.jar -jar <my-app>.jar
```
