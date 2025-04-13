FROM eclipse-temurin:21-jdk-alpine
VOLUME /app
ENV MONGO_PORT=27017
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080