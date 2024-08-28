FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /home/app
COPY src ./src
COPY pom.xml .
RUN mvn clean package -DskipTests

FROM openjdk:17-oracle
WORKDIR /usr/local/lib
COPY --from=build /home/app/target/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]