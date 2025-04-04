
FROM maven:3.9-amazoncorretto-17-alpine AS build

WORKDIR /app


RUN apk update && apk add --no-cache git


RUN git clone --depth 1 --branch main --single-branch https://github.com/anonimushackab/basi2.git progetto && cd progetto && mvn package #-DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/progetto/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]