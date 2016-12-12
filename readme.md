# Microservices-Demo mit Spring Boot Netflix

## 2 fachliche Microservices

Im Rahmen der Demo der Microservice Architektur mit Spring Boot Netflix werden
zwei fachliche Microservices benötigt. Ein einzelner Serivce reicht nicht aus
weil hier der synchrone Aufruf von einem Microservice zu einem anderen 
dargestellt werden soll.

Die hier implementierten Microservies sind

1. [Hello-World](./hello-world) - Ein klassischer "Hello World!"-Service.
2. [Primes](./primes) - Ein Service der Primzahlen berechnet.

## Ausführung der Demo

Die Services können am einfachsten mittels einer IDE gestartet werden oder als 
ausführbare JAR-Datei gepackt und ausgeführt werden.

## Referenzen

1. [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
2. [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest/)
