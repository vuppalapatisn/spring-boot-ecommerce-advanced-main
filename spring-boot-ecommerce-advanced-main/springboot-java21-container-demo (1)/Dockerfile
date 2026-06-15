# -------- Build Stage --------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -q -e -B dependency:resolve

COPY src ./src
RUN mvn -q -DskipTests package

# -------- Runtime Stage --------
FROM gcr.io/distroless/java21-debian12
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java",
 "-XX:+UseContainerSupport",
 "-XX:MaxRAMPercentage=70",
 "-XX:InitialRAMPercentage=40",
 "-XX:+ExitOnOutOfMemoryError",
 "-XX:+AlwaysPreTouch",
 "-jar","app.jar"]
