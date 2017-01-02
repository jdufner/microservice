# Microservices-Demo mit Spring Boot Netflix

## 2 fachliche Microservices

Im Rahmen der Demo der Microservice Architektur mit Spring Boot Netflix werden
zwei fachliche Microservices benötigt. Ein einzelner Serivce reicht nicht aus
weil hier der synchrone Aufruf von einem Microservice zu einem anderen 
dargestellt werden soll.

Die hier implementierten Microservices sind

1. [Hello-World](./hello-world) - Ein klassischer "Hello World!"-Service.
2. [Primes](./primes) - Ein Service der Primzahlen berechnet.

Neben den fachlichen Services werden Infrastrukturservices benötigt: 

1. [Service-Registry](./service-registry) - Ein Service-Registry und -Discovery.
2. [Monitoring-Application](./monitor) - Ein Dashboard zur Überwachung und 
   Aggration von Hystrix-Stream.
3. [Service-Gateway](./gateway) - Ein Service-Gateway zur Weiterleitung und 
   Filterung der Serviceaufrufe (Reverse-Proxy).

Alle Services verfügen über einen Health-Endpoint um über den aktuellen Zustand 
Auskunft zu geben.

## Ausführung der Demo

### Maven

Voraussetzung: Der User, der den Maven-Build ausführt, muss `docker` ausführen
können. Dazu muss der User Mitglied der Gruppe docker sein: 
`sudo usermod -aG docker $USER`, ggf. muss sich der User danach noch aus- und
einloggen.

Mit `mvn package docker:build` kann das Image gebaut werden. Das Image ist nun
als lokales Image verfügbar. Das kann mit `docker images -a` überprüft werden. 

### Docker

Die Services können nun nicht mehr aus der IDE gestaret werden, sondern müssen
in einem Docker-Container gestartet werden. Dazu sind folgende Schritte nötig:

Annahme: Es sind keine Docker-Images mit den Services gebaut.

1. Baue und starte alle Docker-Images mit `docker-compose up -d`
2. Nach der Demo wird können die Docker-Images mit `docker-compose stop`
3. Falls neue Docker-Images neu gebaut und gestartet werden sollen, müssen
   die alten Images entfernt werden.

````
sudo docker ps -a | grep Exit | cut -d ' ' -f 1 | xargs sudo docker rm
sudo docker images -a | grep "^<none>" | awk "{print \$3}" | xargs sudo docker rmi -f
sudo docker rmi microserviceeureka_primes microserviceeureka_hello-world microserviceeureka_monitor microserviceeureka_gateway microserviceeureka_service-registry
````

## Referenzen

### Spring Getting Started
1. [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
2. [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest/)
3. [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
4. [Service Registration and Discovery](https://spring.io/guides/gs/service-registration-and-discovery/)
5. [Client Side Load Balancing with Ribbon and Spring Cloud](https://spring.io/guides/gs/client-side-load-balancing/)
6. [Circuit Breaker](https://spring.io/guides/gs/circuit-breaker/)
7. [Routing and Filtering](https://spring.io/guides/gs/routing-and-filtering/)
8. [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)

### Spring Dokumentationen
1. [Spring Framework Reference Documentation](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/) [(single page)](https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/)
2. [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/) [(single page)](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/)
3. [Spring Cloud](http://projects.spring.io/spring-cloud/spring-cloud.html)
4. [Spring CLoud Netflix](http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html)

### Docker
1. [Dockerfile reference](https://docs.docker.com/engine/reference/builder/)
2. [Best practices for writing Dockerfiles](https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/)
3. [Compose file reference](https://docs.docker.com/compose/compose-file/)
4. [A maven plugin for Docker ](https://github.com/spotify/docker-maven-plugin)
