FROM maven:3.9.3-eclipse-temurin-20 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:20-jdk
COPY --from=build /usr/src/app/target/*.jar /usr/app/npvet-api-1.0.0-SNAPSHOT.jar
ENV API_PORTA=8080
ENV DB_HOST=npvet.ch3sxjfqqiwc.us-east-1.rds.amazonaws.com
ENV DB_PORTA=5432
ENV DB_NOME=npvet
ENV DB_USER=postgres
ENV DB_PWD=postgres
ENV DDL=validate
EXPOSE 8080

ENTRYPOINT ["java","-jar","/usr/app/npvet-api-1.0.0-SNAPSHOT.jar"]
