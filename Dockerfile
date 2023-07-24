FROM openjdk:15-alpine
RUN mkdir /usr/myapp
COPY target/cineflix-0.0.1-SNAPSHOT.jar /usr/myapp/app.jar
WORKDIR /usr/myapp
EXPOSE 8090
ENTRYPOINT [ "sh", "-c", "java --enable-preview $JAVA_OPTS -jar app.jar" ]