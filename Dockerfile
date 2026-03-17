FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src

RUN chmod +x gradlew && ./gradlew build -x test

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=0 /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

