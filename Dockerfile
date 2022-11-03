FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN apk --no-cache add curl
RUN curl -u admin:yassine1 -o achat-1.1.jar "http://192.168.1.90:8081/repository/maven-releases/tn/esprit/rh/achat/1.1/achat-1.1.jar" -L
ENTRYPOINT ["java","-jar","/achat-1.1.jar"]
EXPOSE 8089
