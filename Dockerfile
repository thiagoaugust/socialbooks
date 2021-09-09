FROM openjdk

WORKDIR /app

COPY target/socialbooks-0.0.1-SNAPSHOT.jar /app/socialbooks.jar

ENTRYPOINT ["java", "-jar", "socialbooks.jar"]