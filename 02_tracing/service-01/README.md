# DCAC Spring Boot Reference

###     Omar Gaye
###     Last update: 2024-08-25


# WORK IN PROGRESS

## Development environment:
* Spring Boot version: 3.3.2
* Spring Cloud version: 2023.0.2
* Java version: GraalVM JDK 22.0.2
* Gradle version: 8.8

## Topics:
* API documentation
* Web client
* Observability
* Distributed Tracing
* Circuit breaker
* Security
* Exception handling
* Containerization
* OpenShift Deployment
* Service Mesh
* Native image
* API Gateway

## APIs and Frameworks:
* Spring:
 * Spring Boot, Spring Data, Spring Security, Spring Cloud
* API documentation:
  * Open API (Swagger)
* Telemetry, Observability:
  * Open telemetry, Open Tracing, Micrometer, Zipkin, Jaeger, prometheus, Kiali, Grafana, SigNoz
* Security
  * JWT
* CI/CD
  * Tekton, ArgoCD
* Misc:
  * Lombok, GraalVM / Mandrel, Helm, Kustomize


## Workshops:
Each workshop demo a one or a set of APIs/functionalities. A workshop maps to a branch. A Milestone maps to a tag
The main branch reflects the latest
Naming convention:
branches:
wkshop_01_init
wkshop_02_tracing
wkshop_03_observabilty
releases / tags:
release_01_observabilty
release_02_openshift
release_03_service-mesh

### SIGNOZ TELEMETRY
OTEL_RESOURCE_ATTRIBUTES=service.name=springboot-reference \
OTEL_EXPORTER_OTLP_HEADERS="signoz-access-token=884fcda8-f349-4db2-82e0-a8abd6e601bc" \
OTEL_EXPORTER_OTLP_ENDPOINT=https://ingest.us.signoz.cloud:443 \
java -javaagent:<path>/opentelemetry-javaagent.jar -jar <my-app>.jar
