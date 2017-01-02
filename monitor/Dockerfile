FROM openjdk:8-alpine
ADD target/monitor-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -Dlogging.path=/log/ -jar /app.jar" ]
EXPOSE 8080
VOLUME ["/log"]
