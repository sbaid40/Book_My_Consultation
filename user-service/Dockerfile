FROM maven:3.8.1-jdk-11 as build
WORKDIR /usr/src/BookMyConsultation/user-service
COPY src /usr/src/BookMyConsultation/user-service/src
COPY pom.xml /usr/src/BookMyConsultation/user-service
RUN mvn -f /usr/src/BookMyConsultation/user-service/pom.xml clean package

FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
WORKDIR /home/ubuntu/BookMyConsultation/user-service
COPY --from=build /usr/src/BookMyConsultation/user-service/target/user-service-0.0.1-SNAPSHOT.jar /usr/src/BookMyConsultation/user-service/user-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/BookMyConsultation/user-service
ENV PATH = "${PATH}:${JAVA_HOME}/bin"
ENTRYPOINT [ "java", "-jar", "/usr/src/BookMyConsultation/user-service/user-service-0.0.1-SNAPSHOT.jar"]