# Estágio 1: Build (Compilação)
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copia apenas os arquivos de configuração do Gradle primeiro (aproveita o cache de camadas)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Dá permissão de execução e baixa as dependências
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

# Copia o código fonte e gera o JAR
COPY src src
RUN ./gradlew bootJar --no-daemon

# Estágio 2: Runtime (Execução)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# O Gradle gera o jar em build/libs. O "-plain.jar" deve ser ignorado.
COPY --from=build /app/build/libs/*[!plain].jar app.jar

# Configuração de memória para planos gratuitos (evita que o Spring caia)
ENV JAVA_TOOL_OPTIONS="-Xmx300m -Xss512k"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]