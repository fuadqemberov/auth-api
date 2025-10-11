# 1️⃣ Build aşaması
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2️⃣ Run aşaması
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/auth-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
