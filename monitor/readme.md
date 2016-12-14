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
