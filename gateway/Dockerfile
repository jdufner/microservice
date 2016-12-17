FROM openjdk:8-alpine
#VOLUME /tmp
ADD target/gateway-0.0.1-SNAPSHOT.jar .
RUN sh -c 'touch /gateway-0.0.1-SNAPSHOT.jar'
#CMD /usr/bin/java -Dspring.profiles.active=docker -Dlogging.path=/log/ -Xmx250m -Xms250m -jar gateway-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -Dlogging.path=/log/ -jar /gateway-0.0.1-SNAPSHOT.jar" ]
EXPOSE 8080
VOLUME ["/log"]