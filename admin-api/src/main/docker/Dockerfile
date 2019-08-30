FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD admin-api-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "-Dspring.profiles.active=docker","/app.jar"]