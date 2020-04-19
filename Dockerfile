FROM openjdk:14-slim-buster
COPY ./target/client-service-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "--enable-preview", "app.jar"]