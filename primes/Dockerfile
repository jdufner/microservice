FROM openjdk:8-alpine
RUN apk add --update bash && rm -rf /var/cache/apk/*
ADD ./wait-for-it.sh wait-for-it.sh
ADD target/primes-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "./wait-for-it.sh configuration:8080 -t 100 -- java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]
EXPOSE 8080
