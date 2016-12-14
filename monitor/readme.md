# Monitor - Microservice

## Funktion

Die Monitoring-Application ist ein Dienst zur Überwachung der Last und 
Verfügbarkeit von Services. Der Serivce aggregiert die Hystrix-Streams 
unterschiedlicher Service in einem Dashboard.

## Implementierung

Die Implementierung stammt vollständig von Spring Boot Netflix. In diesem 
Projekt findet nur die Konfiguration statt. Dazu ein paar Bemerkungen:

1. Das Hystrix-Dashboard wird mittels einer Dependency und einer Annotation
   aktiviert. 
2. Das Hystrix-Dashboard kann entweder einen einzelnen Hystrix-Stream anzeigen
   oder einen mittels Turbine aggregierten Stream.

## Test

Die Monitoring-Applikation kann im Browser unter `http://localhost:8180/hystrix` 
aufgerufen werden. Im Hystrix-Dashboard kann der Hystrix- oder Turbine-Stream
angegeben werden, welcher im Dashboard angezeigt werden soll. Beispiele der 
Streams:

* Hello-World: `http://localhost:9090/hystrix.stream`
* Primes: `http://localhost:9190/hystrix.stream`
* Turbine (aggregiert): `http://localhost:8180/turbine.stream`

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
java -jar monitor-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker
````

oder

````
java -jar -Dspring.profiles.active=docker monitor-0.0.1-SNAPSHOT.jar
````
