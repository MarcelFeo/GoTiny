# Estágio 1: Build
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

COPY src src
# Compila e move o JAR gerado para um nome fixo
RUN ./gradlew bootJar --no-daemon && \
    mv build/libs/*[!plain].jar build/libs/app.jar

# Estágio 2: Runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /

# Agora copiamos o arquivo com nome já garantido
COPY --from=build /app/build/libs/app.jar /app.jar

# Configuração de memória para planos gratuitos
ENV JAVA_TOOL_OPTIONS="-Xmx300m -Xss512k"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]