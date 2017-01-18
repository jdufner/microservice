# Microservices-Demo mit Spring Boot Netflix

## 2 fachliche Microservices

Im Rahmen der Demo der Microservice Architektur mit Spring Boot Netflix werden
zwei fachliche Microservices benötigt. Ein einzelner Serivce reicht nicht aus
weil hier der synchrone Aufruf von einem Microservice zu einem anderen 
dargestellt werden soll.

Die hier implementierten Microservices sind

1. [Hello-World](./hello-world) - Ein klassischer "Hello World!"-Service.
2. [Primes](./primes) - Ein Service der Primzahlen berechnet.

Neben den fachlichen Services werden Infrastrukturservices benötigt: 

1. [Service-Registry](./service-registry) - Ein Service-Registry und -Discovery.
2. [Monitoring-Application](./monitor) - Ein Dashboard zur Überwachung und 
   Aggration von Hystrix-Stream.
3. [Service-Gateway](./gateway) - Ein Service-Gateway zur Weiterleitung und 
   Filterung der Serviceaufrufe (Reverse-Proxy).

Alle Services verfügen über einen Health-Endpoint um über den aktuellen Zustand 
Auskunft zu geben.

## Ausführung der Demo

Die Services können nun nicht mehr aus der IDE gestaret werden, sondern müssen
in einem Docker-Container gestartet werden. Dazu sind folgende Schritte nötig:

Annahme: Es sind keine Docker-Images mit den Services gebaut.

1. Baue und starte alle Docker-Images mit `docker-compose up -d`
2. Nach der Demo wird können die Docker-Images mit `docker-compose stop`
3. Falls neue Docker-Images neu gebaut und gestartet werden sollen, müssen
   die alten Images entfernt werden.

````
sudo docker ps -a | grep Exit | cut -d ' ' -f 1 | xargs sudo docker rm
sudo docker images -a | grep "^<none>" | awk "{print \$3}" | xargs sudo docker rmi -f
sudo docker rmi microserviceeureka_primes microserviceeureka_hello-world microserviceeureka_monitor microserviceeureka_gateway microserviceeureka_service-registry microserviceeureka_logstash microserviceeureka_elasticsearch microserviceeureka_kibana
````

Docker-Images können bspw. mittels `docker-compose scale primes=2` skaliert 
werden.

Der ELK-Stack kann unter der URL `localhost:5601` aufgerufen werden.

## ELK-Stack

Der ELK-Stack ist ein Quasi-Standard zur Behandlung von Log-Informationen. ELK
steht für E = [Elasticsearch](https://www.elastic.co/de/products/elasticsearch),
L = [Logstash](https://www.elastic.co/products/logstash) und K = 
[Kibana](https://www.elastic.co/de/products/kibana). Elasticsearch ist eine 
Volltextsuche, Logstash ist ein Filter- und Transformationspipeline und Kibana
ist eine Daten-Visualisierung.

Die Konfiguration des ELK-Stack ist ein wenig knifflig und leider nur auf 
wenigen Seiten (siehe Referenzen) übersichtlich dargestellt. Meine 
Konfiguration hier basiert auf den offiziellen Docker-Images. Die Konfiguration
erfolgt in angepassten Dockerfiles.

### Konfiguration für Elasticsearch

Elasticsearch ist sehr ressourcenhungrig. Daher muss noch folgende 
Konfiguration durchgeführt werden.

https://www.elastic.co/guide/en/elasticsearch/guide/current/_file_descriptors_and_mmap.html

Elasticsearch also uses a mix of NioFS and MMapFS for the various files. Ensure 
that you configure the maximum map count so that there is ample virtual memory 
available for mmapped files. This can be set temporarily:

````
sysctl -w vm.max_map_count=262144
````

Or you can set it permanently by modifying `vm.max_map_count` setting in your 
`/etc/sysctl.conf`.

### Distributed Log Correlation with Spring Cloud Sleuth
As microservices tend to call each other, the ability correlate log entries across several 
service instances becomes very important.
When looking at the logfile output of 'gateway", 'hello-world' and 'primes' you will see, 
that tracing information is being added to some log entries. These can also be seen in Kibana.
These are being added by Spring Cloud Sleuth.

You will also see, that the "traceId" stays the same while gateway calls hello-world which 
in turn calls primes. This is the so called tracingId that is being transferred across service calls by Sleuth using HTTP Headers. This makes correlation of entries accross services easy.


## Referenzen

### Spring Getting Started
1. [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
2. [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest/)
3. [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
4. [Service Registration and Discovery](https://spring.io/guides/gs/service-registration-and-discovery/)
5. [Client Side Load Balancing with Ribbon and Spring Cloud](https://spring.io/guides/gs/client-side-load-balancing/)
6. [Circuit Breaker](https://spring.io/guides/gs/circuit-breaker/)
7. [Routing and Filtering](https://spring.io/guides/gs/routing-and-filtering/)
8. [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)

### Spring Dokumentationen
1. [Spring Framework Reference Documentation](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/) [(single page)](https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/)
2. [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/) [(single page)](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/)
3. [Spring Cloud](https://projects.spring.io/spring-cloud/spring-cloud.html)
4. [Spring Cloud Netflix](https://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html)
5. [Spring Cloud Sleuth](https://cloud.spring.io/spring-cloud-sleuth/index.html)

### Docker
1. [Dockerfile reference](https://docs.docker.com/engine/reference/builder/)
2. [Best practices for writing Dockerfiles](https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/)
3. [Compose file reference](https://docs.docker.com/compose/compose-file/)

### ELK-Stack
1. [Docker ELK stack](https://github.com/deviantony/docker-elk)
2. [Building an ELK stack with docker-compose](http://blog.kopis.de/2016/01/26/building-an-elk-stack-with-docker-compose/)
3. [Manage Spring Boot Logs with Elasticsearch, Logstash and Kibana](http://knes1.github.io/blog/2015/2015-08-16-manage-spring-boot-logs-with-elasticsearch-kibana-and-logstash.html)
