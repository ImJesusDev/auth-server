FROM openjdk:12-alpine
VOLUME /tmp
ADD ./target/auth-server-0.0.1-SNAPSHOT.jar auth-server.jar 
ENTRYPOINT ["java","-jar","/auth-server.jar"]