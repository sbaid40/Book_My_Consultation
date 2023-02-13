FROM maven:3.8.1-jdk-11 as build
WORKDIR /usr/src/BookMyConsultation/appointment-service
COPY src /usr/src/BookMyConsultation/appointment-service/src
COPY pom.xml /usr/src/BookMyConsultation/appointment-service
RUN mvn -f /usr/src/BookMyConsultation/appointment-service/pom.xml clean package

FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
WORKDIR /home/ubuntu/BookMyConsultation/appointment-service
COPY --from=build /usr/src/BookMyConsultation/appointment-service/target/appointment-service-0.0.1-SNAPSHOT.jar /usr/src/BookMyConsultation/appointment-service/appointment-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/BookMyConsultation/appointment-service
ENV PATH = "${PATH}:${JAVA_HOME}/bin"
ENTRYPOINT [ "java", "-jar", "/usr/src/BookMyConsultation/appointment-service/appointment-service-0.0.1-SNAPSHOT.jar"]