package de.jdufner.microservice.hello.world;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jdufner on 08.12.16.
 */
@RestController
@EnableAutoConfiguration
public class HelloWorldController {

  @RequestMapping(path = "/")
  public String helloWorld() {
    return "Hello World!";
  }

}
