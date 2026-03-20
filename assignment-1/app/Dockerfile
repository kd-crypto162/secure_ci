##
## Multi-stage build:
## - builder image contains tooling (Gradle/JDK)
## - runtime image is minimal and non-root
##

FROM gradle:8.11.1-jdk17 AS build
WORKDIR /workspace

COPY build.gradle settings.gradle ./
COPY config ./config
COPY src ./src

RUN gradle --no-daemon clean test jar

FROM gcr.io/distroless/java17-debian12:nonroot
WORKDIR /app

COPY --from=build /workspace/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]

