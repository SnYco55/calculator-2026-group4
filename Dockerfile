FROM maven:3.9.14-eclipse-temurin-25 AS builder

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]