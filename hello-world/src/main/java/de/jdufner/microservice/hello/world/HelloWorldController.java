package de.jdufner.microservice.hello.world;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jdufner on 08.12.16.
 */
@RestController
@EnableAutoConfiguration
public class HelloWorldController {

  private final static String PRIMES = "primes";

  @Autowired
  private DiscoveryClient discoveryClient;

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  private RestTemplate restTemplate = new RestTemplate();

  @RequestMapping("/service-instances/{applicationName}")
  public List<ServiceInstance> serviceInstancesByApplicationName(
      @PathVariable String applicationName) {
    return this.discoveryClient.getInstances(applicationName);
  }

  @RequestMapping(path = "/")
  @HystrixCommand(fallbackMethod = "getPrimesUpTo100")
  public String helloWorld() {
    return getPrimesSmallerThan(1000);
  }

  private String getPrimesSmallerThan(int limit) {
    URI uri = getUriBalanced();
    List<Integer> primes = restTemplate.getForObject(uri, List.class);
    List<Integer> limitedPrimes = primes.stream().filter(i -> i < limit).collect(Collectors.toList());
    return limitedPrimes.toString();
  }

  private URI getUri() {
    return discoveryClient.getInstances(PRIMES).get(0).getUri();
  }

  private URI getUriBalanced() {
    return loadBalancerClient.choose(PRIMES).getUri();
  }

  private String getPrimesUpTo100() {
    List primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
    return primes.toString();
  }
}
