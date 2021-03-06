# Service Registry - Microservice

## Funktion

Die Service-Registry ist ein Dienst für Services und sich selbst dort zu 
registrieren. Die Service-Discovery ist ein Dienst für Clients oder Services
um andere Services zu finden.

Hintergrund: In verteilten Umgebungen werden zur Lastverteilung neue Services 
deployt oder auf andere Hosts umgezogen. Die Service-Registry ist die 
zentrale Stelle in der alle Dienste bekannt sind. Der Client benötigt nur den 
Namen des Service und erhält die technischen Informationen um den Service
aufrufen zu können.

## Implementierung

Die Implementierung stammt vollständig von Spring Boot Netflix. In diesem 
Projekt findet nur die Konfiguration statt. Dazu ein paar Bemerkungen:

1. Wir starten Eureka im stand-alone Mode. Es gibt also keine weitere Eureka-
   Instanzen, die nach dem Inhalt der Registry befragt werden können / müssen.
   Eureka weiß nur das, was andere Serivces ihm mitteilen.
2. Wir arbeiten mit IP-Adressen. Bei Service-Discovery lassen wir uns die IP-
   Adresse des Service geben, nicht den Hostnamen. Im Falle einer Docker-
   Umgebung sind die Hostnamen die Container-IDs. Die sind noch schlechter 
   lesbar als eine IP-Adresse und könnte sowieso nicht aufgelöst werden, weil
   sie keine DNS-Eintrag haben.

## Test

Die Service-Registry kann im Browser unter `http://localhost:8761` aufgerufen 
werden.

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
java -jar service-registry-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker
````

oder

````
java -jar -Dspring.profiles.active=docker service-registry-0.0.1-SNAPSHOT.jar
````
