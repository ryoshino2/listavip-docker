FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/spring-boot-docker-*.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker","-jar", "/app.jar"]