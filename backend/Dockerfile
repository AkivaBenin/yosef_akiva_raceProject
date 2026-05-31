# Step 1: Use Maven to build the Java code
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Use a supported, clean Java environment to run the built app
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
# This wild card ensures Docker finds your jar file no matter what it is named
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]