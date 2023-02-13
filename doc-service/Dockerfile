FROM maven:3.8.1-jdk-11 as build
WORKDIR /usr/src/BookMyConsultation/doc-service
COPY src /usr/src/BookMyConsultation/doc-service/src
COPY pom.xml /usr/src/BookMyConsultation/doc-service
RUN mvn -f /usr/src/BookMyConsultation/doc-service/pom.xml clean package

FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
WORKDIR /home/ubuntu/BookMyConsultation/doc-service
COPY --from=build /usr/src/BookMyConsultation/doc-service/target/doctor-service-0.0.1-SNAPSHOT.jar /usr/src/BookMyConsultation/doc-service/doctor-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/BookMyConsultation/doc-service
ENV PATH = "${PATH}:${JAVA_HOME}/bin"
ENTRYPOINT [ "java", "-jar", "/usr/src/BookMyConsultation/doc-service/doctor-service-0.0.1-SNAPSHOT.jar"]