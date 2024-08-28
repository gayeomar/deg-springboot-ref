FROM gradle:jdk22-graal AS build

COPY --chown=gradle:gradle . /project
WORKDIR /project

RUN gradle build --no-daemon

FROM openjdk:22-jdk-slim

EXPOSE 8080

COPY --from=build /project/build/libs/*.jar hello.jar

ENTRYPOINT ["java","-jar","hello.jar"]