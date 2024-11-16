# Use uma imagem base com o OpenJDK 21
FROM openjdk:21-jdk

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR da aplicação para o contêiner
COPY target/cineflix-0.0.1-SNAPSHOT.jar /app/cineflix-api.jar

# Define variáveis de ambiente (opcional, pode configurar no docker-compose.yml também)
ENV SPRING_PROFILES_ACTIVE=docker

# Expõe a porta 8080
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "/app/cineflix-api.jar"]
