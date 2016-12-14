# Primes - Microservice

## Funktion

Dieser Service liefert beim Aufruf auf Contextroot "/" ein JSON-Objekt zurück 
mit einer ID, und den Primzahlen unter einer Million zurück: 
`[2,3,5,7,11,13,17,19,23,29, .. 999961,999979,999983]`

## Implementierung

Die Implementierung es Primzahlen-Algorithmus ist ein einfacher 
[Primzahltest] (https://de.wikipedia.org/wiki/Primzahltest). Im Moment sind 
zwei Varianten implementiert:

1. Die erste Variante iteriert über alle Ganzzahlen kleiner der Quadratwurzel
   und bricht ab, sobald ein Teiler gefunden wurde. Existiert kein Teiler, muss
   es sich um eine Primzahl handeln.
2. Die zweite Variante iteriert über die bisher gefundenen Primzahlen kleiner
   der Quadratwurzel und bricht ab, sobald ein Teiler gefunden wurde. Existiert 
   kein Teiler, muss es sich um eine Primzahl handeln.

Der Algorithmus zählt die Anzahl der Divisionen, die nötig sind um alle 
Primzahlen zu identifizieren.

## Test

Dieser Service kann einfach durch Aufruf der URL `http://<host>:<port>/`
aufgerufen werden oder aus einer Bash mit `curl <host>:<port>`. In Fehlerfall 
kann der HTTP-Header mit `curl -i <host>:<port>` ausgegeben werden.

Der Wert der höchsten Primzahl kann über den HTTP-Parameter "maxPrimeNumber" 
gesetzt werden.

````
curl localhost:9190/?maxPrimeNumber=100 | json_pp
````

Liefert ein JSON-Objekt der Art:

````
{
  "id" : 1,
  "primeNumbers" : [2, 3, 5, ... 97]
}
````

## Actuator

Spring Boot liefert sog. Actuators mit. Wir aktivieren die Actuators, weil sie
von die Netflix-Services für die Überwachung benötigt werden. Der für uns 
wichtigste Actuator ist der Health-Endpoint. Er ist unter `/health` erreichbar.

````
curl localhost:9190/health | json_pp
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
java -jar primes-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker
````

oder

````
java -jar -Dspring.profiles.active=docker primes-0.0.1-SNAPSHOT.jar
````
