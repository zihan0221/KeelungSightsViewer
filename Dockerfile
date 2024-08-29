FROM maven:3.8.4-openjdk-17 AS build
#ARG DB_NAME
#ARG DB_PASSWORD
#ENV DB_password=${DB_PASSWORD}
#ENV DB_name=${DB_PASSWORD}
WORKDIR /home/app
COPY src ./src
COPY pom.xml .
RUN mvn clean package -DskipTests

FROM openjdk:17-oracle
COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/demo.jar"]
#ENTRYPOINT ["sh", "-c", "ls && tail -f /dev/null"]