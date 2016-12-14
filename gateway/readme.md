# Gateway - Microservice

## Funktion

Das Service-Gateway ist ein Routing- und Filter-Dienst von Services. Das 
Service-Gateway arbeitet als ein Reverse Proxy und dient der Vereinfachung
von Serviceaufrufen, insbesondere für andere Clients.

## Implementierung

Die Implementierung stammt vollständig von Spring Boot Netflix. In diesem 
Projekt findet nur die Konfiguration statt.

## Test

Die Service-Gateway läuft unter `http://localhost:8080/`. Pfade zu den 
Services:

* Hello-World: `http://localhost:8080/helloworld`
* Primes: `http://localhost:8080/primes`
