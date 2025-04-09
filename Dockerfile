FROM eclipse-temurin:21-jdk-alpine
VOLUME /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080