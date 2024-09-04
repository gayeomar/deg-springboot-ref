FROM gradle:jdk22-graal AS build

COPY --chown=gradle:gradle . /project
WORKDIR /project

RUN gradle build --no-daemon

FROM openjdk:22-jdk-slim

EXPOSE 8082

COPY --from=build /project/build/libs/*.jar service-03.jar

ENTRYPOINT ["java","-jar","service-03.jar"]