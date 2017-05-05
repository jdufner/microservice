package de.jdufner.microservice.konto.beauskunftung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@EnableDiscoveryClient
@SpringBootApplication
public class KontoBeauskunftungApplication {

  public static void main(String[] args) {
    SpringApplication.run(KontoBeauskunftungApplication.class, args);
  }

}
