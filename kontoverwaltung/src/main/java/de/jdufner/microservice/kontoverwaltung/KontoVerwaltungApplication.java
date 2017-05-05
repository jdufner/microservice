package de.jdufner.microservice.kontoverwaltung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@EnableDiscoveryClient
@SpringBootApplication
public class KontoVerwaltungApplication {

  public static void main(String[] args) {
    SpringApplication.run(KontoVerwaltungApplication.class, args);
  }

}
