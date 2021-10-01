#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/formula1/src
COPY pom.xml /home/formula1
RUN mvn -f /home/formula1/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/formula1/target/formula1-0.0.1-SNAPSHOT.jar /usr/local/lib/formula1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/formula1.jar"]