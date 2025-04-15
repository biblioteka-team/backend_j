FROM openjdk:23-jdk-oracle

WORKDIR /app

COPY . .

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080



#WORKDIR /app
#
#COPY
#
#CMD ["java","-jar","/app.jar"]




