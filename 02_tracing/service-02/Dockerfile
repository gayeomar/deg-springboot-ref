FROM gradle:jdk22-graal AS build

COPY --chown=gradle:gradle . /project
WORKDIR /project

RUN gradle build --no-daemon

FROM --platform=$BUILDPLATFORM openjdk:22

EXPOSE 8082

COPY --from=build /project/build/libs/service-02-0.0.1-SNAPSHOT.jar service-02.jar

ENTRYPOINT ["java","-jar","service-02.jar"]