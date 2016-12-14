FROM openjdk:8-alpine
#VOLUME /tmp
ADD target/primes-0.0.1-SNAPSHOT.jar .
RUN sh -c 'touch /primes-0.0.1-SNAPSHOT.jar'
#CMD /usr/bin/java -Dspring.profiles.active=docker -Xmx250m -Xms250m -jar primes-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /primes-0.0.1-SNAPSHOT.jar" ]
EXPOSE 8080