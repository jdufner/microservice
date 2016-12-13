/*
 * Copyright 2016, Jürgen Dufner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.jdufner.microservice.hello.world;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@RestController
@EnableAutoConfiguration
public class HelloWorldController {

  private final AtomicLong counter = new AtomicLong();
  
  @Autowired
  private LoadBalancerClient loadBalancerClient;

  private RestTemplate restTemplate = new RestTemplate();
  private String PRIMES = "primes";

  @RequestMapping(path = "/")
  @HystrixCommand(fallbackMethod = "helloWorldFallback")
  public Greeting helloWorld(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception {
    return doHelloWorld(name, getPrimesSmallerThan(1000));
  }

  public Greeting helloWorldFallback(String name) {
    return doHelloWorld(name, getPrimesUpTo100());
  }

  public Greeting doHelloWorld(String name, final List<Integer> primes) {
    Greeting greeting = new Greeting();
    greeting.setId(counter.incrementAndGet());
    greeting.setMessage("Hello " + name + "!");
    greeting.setPrimes(primes);
    greeting.setHostname(getHostname());
    return greeting;
  }

  private List<Integer> getPrimesSmallerThan(int limit) throws Exception {
    URI uri = loadBalancerClient.choose(PRIMES).getUri();
    PrimeNumbers primes = restTemplate.getForObject(uri, PrimeNumbers.class);
    List<Integer> limitedPrimes = primes.getPrimeNumbers().stream().filter(i -> i < limit).collect(Collectors.toList());
    primes.setPrimeNumbers(limitedPrimes);
    return limitedPrimes;
  }

  private List<Integer> getPrimesUpTo100() {
    return Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
  }

  private String getHostname() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

}
