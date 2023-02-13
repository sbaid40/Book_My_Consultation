FROM maven:3.8.1-jdk-11 as build
WORKDIR /usr/src/BookMyConsultation/eureka-service
COPY src /usr/src/BookMyConsultation/eureka-service/src
COPY pom.xml /usr/src/BookMyConsultation/eureka-service
RUN mvn -f /usr/src/BookMyConsultation/eureka-service/pom.xml clean package

FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
WORKDIR /home/ubuntu/BookMyConsultation/eureka-service
COPY --from=build /usr/src/BookMyConsultation/eureka-service/target/eureka-service-0.0.1-SNAPSHOT.jar /usr/src/BookMyConsultation/eureka-service/eureka-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/BookMyConsultation/eureka-service
ENV PATH = "${PATH}:${JAVA_HOME}/bin"
ENTRYPOINT [ "java", "-jar", "/usr/src/BookMyConsultation/eureka-service/eureka-service-0.0.1-SNAPSHOT.jar"]