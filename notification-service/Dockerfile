FROM maven:3.9-eclipse-temurin-25 as build
WORKDIR /usr/src/BookMyConsultation/notification-service
COPY src /usr/src/BookMyConsultation/notification-service/src
COPY pom.xml /usr/src/BookMyConsultation/notification-service
RUN mvn -f /usr/src/BookMyConsultation/notification-service/pom.xml clean package

FROM eclipse-temurin:25-jre-alpine
MAINTAINER upgrad
WORKDIR /home/ubuntu/BookMyConsultation/notification-service
COPY --from=build /usr/src/BookMyConsultation/notification-service/target/notification-service-0.0.1-SNAPSHOT.jar /usr/src/BookMyConsultation/notification-service/notification-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/BookMyConsultation/notification-service
ENV PATH="${PATH}:${JAVA_HOME}/bin"
ENTRYPOINT [ "java", "-jar", "/usr/src/BookMyConsultation/notification-service/notification-service-0.0.1-SNAPSHOT.jar"]