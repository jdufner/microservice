# Configuration - Microservice

## Funktion

Dieser Service stellt eine zentralisierte Configuration für die Microservice
Architektur (Cloud Services) zur Verfügung. Im vorliegenden Fall werden die 
Konfiguration über ein lokales Git-Repository verwaltet.

## Implementierung

### Git-Server

Voraussetzung: Für die Konfiguration wird ein git-Repository benötigt. Das 
kann bspw. Github sein oder ein unternehmensweites Repository oder ein git-
Server, der ebenfalls über Docker-Image betrieben wird. Im vorliegenden Fall
zeige ich das am Beispiel des Docker-Images [git-ssh-server](https://github.com/unixtastic/git-ssh-server).
Ein vergleichbares Docker-Image ist [git-server-docker](https://github.com/jkarlosb/git-server-docker).
In beiden Fällen wird ein einfaches Docker-Image gestartet, das einen SSH-
Service bereitstellt, über den man auf die Repositories zugreifen kann. Der
Zugriff wird durch Einfügen eines SSH-Keys in authorized-keys gewährt. Neue 
Repositories können einfach über `git init --bare` einem entsprechenden 
Verzeichnis angelegt werden. 

Einschränkung: In dieser Lösung kann nur über ein genereller 
Zugriff auf alle git-Repositories eingerichtet werden. Ein feingranulare 
Zugriffsteuerung auf einzelne Repositories oder gar einzelne Branches ist damit
_nicht_ möglich. Dazu wird eine größere Lösung wie bspw. [Gitlab](https://about.gitlab.com/),
[Bitbucket](https://de.atlassian.com/software/bitbucket) etc benötigt.

### Service

Die Implementierung stammt vollständig von Spring Cloud Config. In diesem 
Projekt findet nur die Konfiguration statt.

In diesem Projekt muss natürlich auch der Zugriff auf das Git-Repository 
mittels SSH konfiguriert werden. Dazu wird ein hier entsprechendes 
Public-Private-Key-Paar hinterlegt. Der Public-Key muss im Git-Server in der
`authorized_keys` eingetragen sein.

## Test

### Git-Server

Der Git-Server selbst ist gut dokumentiert uns sollte selbständig getestet 
werden. Der kann dann natürlich auch für andere Zwecke verwendet werden.

### Configuration Server

Die Service-Gateway läuft unter 
`http://localhost:8280/<spring.application.name>/<profile>[/<label>]`. Pfade 
zu den Services:

* Hello-World: `http://localhost:8280/hello-world/docker[/master]`

### Configuration Client

Prüfe ob der Client richtig gestartet ist, bspw. im Logfile:

````
2017-01-06 17:40:27.293  INFO 6886 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at: http://localhost:8280
2017-01-06 17:40:28.429  INFO 6886 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Located environment: name=hello-world, profiles=[local], label=master, version=8e928e9ded1fba682fc9f82fb4790646461f0731, state=null
2017-01-06 17:40:28.431  INFO 6886 --- [           main] b.c.PropertySourceBootstrapConfiguration : Located property source: CompositePropertySource [name='configService', propertySources=[MapPropertySource [name='configClient'], MapPropertySource [name='/home/jdufner/config-repo/hello-world-local.yml'], MapPropertySource [name='/home/jdufner/config-repo/hello-world.yml']]]
2017-01-06 17:40:28.452  INFO 6886 --- [           main] d.j.m.hello.world.HelloWorldApplication  : The following profiles are active: local
````

### Priorität der Konfiguration

Die Konfiguration greift in folgenden Reihenfolge:

1. Die zentralisierte Konfiguration wird vor der lokalen Konfiguration gezogen.
2. Die spezifische Konfiguration wird vor der allgemeinen Konfiguration gezogen.

Beispiel:

1. Git-Repo:/hello-world-local.properties
2. Git-Repo:/git-repo/hello-world.properties
3. Applikation:/hello-world-local.properties
4. Applikation:/hello-world.properties

### Refresh der Konfiguration

Wenn Actuator aktiviert sind, was hier in der Demo der Fall ist, können die 
Werte zur Laufzeit angepasst werden:

1. Ändere Konfiguration in Git (commit + push)
2. Befehlt Client die Übernahme der Änderung mit `curl -X POST 
   localhost:9090/refresh`
