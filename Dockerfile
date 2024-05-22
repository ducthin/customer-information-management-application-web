FROM openjdk:17.0.1-oracle as build
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]