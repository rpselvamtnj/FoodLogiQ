FROM openjdk:8-jdk-alpine
VOLUME /temp
copy ./target/food-logiq-0.0.1-SNAPSHOT.jar food-logiq-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/food-logiq-0.0.1-SNAPSHOT.jar"]