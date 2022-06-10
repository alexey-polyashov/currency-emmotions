FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=build/libs/currency-emmotion-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/application.jar

WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","application.jar"]
