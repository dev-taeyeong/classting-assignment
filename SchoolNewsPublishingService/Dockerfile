FROM gradle:7.2-jdk17 AS build
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle build

FROM openjdk:17-slim
EXPOSE 8081
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
