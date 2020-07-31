FROM openjdk:12
VOLUME /tmp
WORKDIR '/app'
EXPOSE 9100
COPY . .
RUN ./mvnw clean install 
ENTRYPOINT java -jar /app/target/auth-server-0.0.1-SNAPSHOT.jar