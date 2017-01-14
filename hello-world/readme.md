# Hello World - Microservice

## Funktion

Dieser Service liefert beim Aufruf auf Contextroot "/" ein JSON-Objekt zurück 
mit einer ID, einem String "Hello World!" und eine Liste der Primzahlen unter 
1.000 zurück. Der Service ist damit zur Laufzeit abhängig vom Service 
[primes](../primes). Wenn der Service nicht verfügbar ist, dann wird eine 
Fehlermeldung geliefert.

## Implementierung

Der Aufruf des Primes-Services erfolgt mittels JSON. Das Objekt zum 
deserialisieren ist in diesem Projekt implementiert, weil keine binäre 
Abhängigkeit zum Projekt primes erwünscht ist.

### Konfiguration

Der Hello World-Service erhält nun seine Konfiguration vom Configuration-
Server. Der Start von Hello World muss solange verzögert werden, bis der
Configuration-Server up ist oder das Timeout abgelaufen ist. Das wird mit dem 
Skript  [vishnubob/wait-for-it](https://github.com/vishnubob/wait-for-it)
gemacht. Leider gibt es mit dem Skript ein Problem mit dem Timeout-Befehl. Ich
habe auf [jdufner/wait-for-it](https://github.com/jdufner/wait-for-it) eine für
[Alpine-Linux](https://alpinelinux.org/) korrigierte Version zur Verfügung 
gestellt. BTW: Das Skript benötigt eine BASH, die muss auch noch in das  
Docker-Image von Alpine-Linux eingebaut werden.

## Test

Dieser Service kann einfach durch Aufruf der URL `http://<host>:<port>/`
aufgerufen werden oder aus einer Bash mit `curl <host>:<port>`. In Fehlerfall 
kann der HTTP-Header mit `curl -i <host>:<port>` ausgegeben werden.

Der Wert "World" ist der Default-Value für den Parameter "name". Der Parameter
kann auch einfach als HTTP-Parameter gesetzt werden.

````
curl localhost:9090/?name=Jürgen | json_pp
````

Liefert ein JSON-Objekt der Art:

````
{
  "id": 1,
  "name": "Jürgen",
  "primes": [2, 3, 5, ... 997]
}
````

## Actuator

Spring Boot liefert sog. Actuators mit. Wir aktivieren die Actuators, weil sie
von die Netflix-Services für die Überwachung benötigt werden. Der für uns 
wichtigste Actuator ist der Health-Endpoint. Er ist unter `/health` erreichbar.

````
curl localhost:9090/health | json_pp
````

liefert etwas in der Art

````
{
    "status": "UP",
    "diskSpace": {
        "status": "UP",
        "total": 511397851136,
        "free": 200806346752,
        "threshold": 10485760
    }
}
````

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
java -jar hello-world-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker
````

oder

````
java -jar -Dspring.profiles.active=docker hello-world-0.0.1-SNAPSHOT.jar
````


# Todo
* Ab 10_Docker_Images_skaliert funktioniert der Absprung aus Eureka nicht mehr.
  Eureka lenkt auf die IP-Adresse:Serverport um. Leider ist der Port nicht in
  Docker freigegeben. Wünschenswert wäre, dass der Zugriff via Zuul
  funktioniert.