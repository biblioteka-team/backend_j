# 1. Використовуємо офіційний Maven образ для збірки
FROM maven:3.9.9-eclipse-temurin-23 AS build

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо залежності спочатку для кешування
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw dependency:go-offline -B

# Копіюємо решту проєкту та збираємо jar
COPY . .
RUN ./mvnw clean package -DskipTests

# 2. Використовуємо легкий образ Java для продакшн
FROM eclipse-temurin:23-jdk-alpine

# Створюємо директорію для jar
WORKDIR /app

# Копіюємо jar з етапу білду
COPY --from=build /app/target/biblioteka_backend-*.jar app.jar

# Відкриваємо порт
EXPOSE 8080

# Запускаємо застосунок
ENTRYPOINT ["java", "-jar", "app.jar"]















#FROM openjdk:23-jdk-oracle
#
#WORKDIR /app
#
#COPY . .
#
#ENTRYPOINT ["java","jar"]
#
#EXPOSE 8080



#WORKDIR /app
#
#COPY
#
#CMD ["java","-jar","/app.jar"]




