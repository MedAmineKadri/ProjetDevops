FROM openjdk:8
ADD target/achatproject-1.0.jar achatproject-1.0.jar
EXPOSE 8089
ENTRYPOINT [ "java", "-jar", "achatproject-1.0.jar" ]


