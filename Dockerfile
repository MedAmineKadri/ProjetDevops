FROM openjdk:8
ADD target/achat-1.0.1.jar achat-1.0.1.jar 
EXPOSE 8089
ENTRYPOINT [ "java" , "-jar" , "achat-1.0.1.jar" ]