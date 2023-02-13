FROM maven:3.8.1-jdk-11 as build
WORKDIR /usr/src/BookMyConsultation/payment-service
COPY src /usr/src/BookMyConsultation/payment-service/src
COPY pom.xml /usr/src/BookMyConsultation/payment-service
RUN mvn -f /usr/src/BookMyConsultation/payment-service/pom.xml clean package

FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
WORKDIR /home/ubuntu/BookMyConsultation/payment-service
COPY --from=build /usr/src/BookMyConsultation/payment-service/target/payment-service-0.0.1-SNAPSHOT.jar /usr/src/BookMyConsultation/payment-service/payment-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/BookMyConsultation/payment-service
ENV PATH = "${PATH}:${JAVA_HOME}/bin"
ENTRYPOINT [ "java", "-jar", "/usr/src/BookMyConsultation/payment-service/payment-service-0.0.1-SNAPSHOT.jar"]