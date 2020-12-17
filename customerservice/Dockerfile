FROM openjdk:11-jdk
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar
EXPOSE 9090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker","-jar","/app.jar"]	