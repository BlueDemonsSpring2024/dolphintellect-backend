FROM maven:3.9-amazoncorretto-21 as maven-builder
COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM amazoncorretto:21

COPY .env /app-service/.env
COPY --from=maven-builder app/target/dolphintellect-api.jar /app-service/dolphintellect-api.jar
WORKDIR /app-service

EXPOSE 8080
ENTRYPOINT ["java","-jar","dolphintellect-api.jar"]