FROM openjdk:11

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /usr/app/application.jar
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","application.jar"]


# with precise names
# COPY ./target/application-1.0-SNAPSHOT.jar /usr/app/
# WORKDIR /usr/app
# ENTRYPOINT ["java","-jar","application-1.0-SNAPSHOT.jar"]