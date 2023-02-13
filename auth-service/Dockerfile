FROM maven:3.8.1-jdk-11 as build
WORKDIR /usr/src/BookMyConsultation/auth-service
COPY src /usr/src/BookMyConsultation/auth-service/src
COPY pom.xml /usr/src/BookMyConsultation/auth-service
RUN mvn -f /usr/src/BookMyConsultation/auth-service/pom.xml clean package

FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
WORKDIR /home/ubuntu/BookMyConsultation/auth-service
COPY --from=build /usr/src/BookMyConsultation/auth-service/target/auth-service-0.0.1-SNAPSHOT.jar /usr/src/BookMyConsultation/auth-service/auth-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/BookMyConsultation/auth-service
ENV PATH = "${PATH}:${JAVA_HOME}/bin"
ENTRYPOINT [ "java", "-jar", "/usr/src/BookMyConsultation/auth-service/auth-service-0.0.1-SNAPSHOT.jar"]