FROM maven:3.9-eclipse-temurin-25 as build
WORKDIR /usr/src/BookMyConsultation/rating-service
COPY src /usr/src/BookMyConsultation/rating-service/src
COPY pom.xml /usr/src/BookMyConsultation/rating-service
RUN mvn -f /usr/src/BookMyConsultation/rating-service/pom.xml clean package

FROM eclipse-temurin:25-jre-alpine
MAINTAINER upgrad
WORKDIR /home/ubuntu/BookMyConsultation/rating-service
COPY --from=build /usr/src/BookMyConsultation/rating-service/target/rating-service-0.0.1-SNAPSHOT.jar /usr/src/BookMyConsultation/rating-service/rating-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/BookMyConsultation/rating-service
ENV PATH="${PATH}:${JAVA_HOME}/bin"
ENTRYPOINT [ "java", "-jar", "/usr/src/BookMyConsultation/rating-service/rating-service-0.0.1-SNAPSHOT.jar"]