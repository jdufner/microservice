package de.jdufner.microservice.hello.world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jdufner on 08.12.16.
 */
@RestController
@EnableAutoConfiguration
public class HelloWorldController {

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/service-instances/{applicationName}")
  public List<ServiceInstance> serviceInstancesByApplicationName(
      @PathVariable String applicationName) {
    return this.discoveryClient.getInstances(applicationName);
  }

  @RequestMapping(path = "/")
  public String helloWorld() {
    return "Hello World!";
  }

}
