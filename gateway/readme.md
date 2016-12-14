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

## Profile

Das Projekt verfügt über zwei Profile:

* local
* docker

### local

In diesem Profil laufen alle Services auf localhost und die Ports werden so
umgebogen, dass sich die Services nicht gegenseitig behindern. Die
Konfiguration erfolgt in application-local.properties

Um dieses Profil zu starten muss _kein_ Parameter gesetzt / übergeben werden.

### docker

In diesem Profil laufen alle Service in einem eigenen Docker-Container. Die
Services haben also eine eigene IP-Adresse und können alle auf dem jeweils
gleichen Port laufen, weil sie ja isoliert voneinander sind.

Um dieses Profil zu starten muss das Profil beim Start gesetzt werden. Dazu
stehen zwei Varianten zur Verfügung:

````
java -jar gateway-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker
````

oder

````
java -jar -Dspring.profiles.active=docker gateway-0.0.1-SNAPSHOT.jar
````
