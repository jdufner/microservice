FROM openjdk:8-alpine
ADD src/main/resources/ssh/id_rsa /root/.ssh/id_rsa
ADD src/main/resources/ssh/id_rsa.pub /root/.ssh/id_rsa.pub
ADD src/main/resources/ssh/config /root/.ssh/config
ADD src/main/resources/ssh/known_hosts /root/.ssh/known_hosts
ADD target/configuration-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
# http://www.thezonemanager.com/2015/07/whats-so-special-about-devurandom.html
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]
#EXPOSE 8080
