FROM maven:3.9-eclipse-temurin-25 as build
WORKDIR /usr/src/BookMyConsultation/api-gateway
COPY src /usr/src/BookMyConsultation/api-gateway/src
COPY pom.xml /usr/src/BookMyConsultation/api-gateway
RUN mvn -f /usr/src/BookMyConsultation/api-gateway/pom.xml clean package

FROM eclipse-temurin:25-jre-alpine
MAINTAINER upgrad
WORKDIR /home/ubuntu/BookMyConsultation/api-gateway
COPY --from=build /usr/src/BookMyConsultation/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar /usr/src/BookMyConsultation/api-gateway/api-gateway-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/BookMyConsultation/api-gateway
ENV PATH="${PATH}:${JAVA_HOME}/bin"
ENTRYPOINT [ "java", "-jar", "/usr/src/BookMyConsultation/api-gateway/api-gateway-0.0.1-SNAPSHOT.jar"]