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
