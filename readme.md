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

Beide Service verfügen über einen Health-Endpoint um über den aktuellen Zustand 
Auskunft zu geben.

## Ausführung der Demo

Die Services können am einfachsten mittels einer IDE gestartet werden oder als 
ausführbare JAR-Datei gepackt und ausgeführt werden.

## Referenzen

### Getting Started
1. [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
2. [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest/)
3. [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
4. [Service Registration and Discovery](https://spring.io/guides/gs/service-registration-and-discovery/)
5. [Client Side Load Balancing with Ribbon and Spring Cloud](https://spring.io/guides/gs/client-side-load-balancing/)
6. [Circuit Breaker](https://spring.io/guides/gs/circuit-breaker/)

### Dokumentationen
1. [Spring Framework Reference Documentation](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/) [(single page)](https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/)
2. [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/) [(single page)](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/)
3. [Spring Cloud](http://projects.spring.io/spring-cloud/spring-cloud.html)
4. [Spring CLoud Netflix](http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html)
