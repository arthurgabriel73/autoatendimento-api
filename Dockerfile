FROM openjdk:17-jdk-alpine
COPY autoatendimento-0.0.3-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]