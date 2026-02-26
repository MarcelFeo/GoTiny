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
# Forçamos o diretório raiz para não haver confusão de caminhos
WORKDIR /

# Copiamos o jar do estágio de build para a raiz do container atual
COPY --from=build /app/build/libs/*[!plain].jar /app.jar

# Garantimos que o arquivo é legível
RUN chmod 644 /app.jar

# Configuração de memória para planos gratuitos
ENV JAVA_TOOL_OPTIONS="-Xmx300m -Xss512k"

EXPOSE 8080

# Usamos o caminho absoluto /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]