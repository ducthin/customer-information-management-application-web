FROM maven:3.8.5-openjdk_17 as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]