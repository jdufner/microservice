package de.jdufner.microservice.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigurationApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigurationApplication.class, args);
  }

}
