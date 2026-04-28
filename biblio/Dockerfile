FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY ./target/*.jar biblio.jar

CMD [ "java", "-jar", "biblio.jar" ]

EXPOSE 8080
