# ---------- build stage ----------
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# copia tudo do projeto
COPY . .

# dá permissão pro gradlew
RUN chmod +x gradlew

# usa o gradle wrapper do projeto (ele baixa o gradle 8.14 automaticamente)
RUN ./gradlew bootJar --no-daemon

# ---------- run stage ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]