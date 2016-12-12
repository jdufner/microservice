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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
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
  private DiscoveryClient discoveryClient;

  private RestTemplate restTemplate = new RestTemplate();
  private String PRIMES = "primes";

  @RequestMapping(path = "/")
  public Greeting helloWorld(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception {
    Greeting greeting = new Greeting();
    greeting.setId(counter.incrementAndGet());
    greeting.setMessage("Hello " + name + "!");
    greeting.setPrimes(getPrimesSmallerThan(1000));
    greeting.setHostname(getHostname());
    return greeting;
  }

  private List<Integer> getPrimesSmallerThan(int limit) throws Exception {
    URI uri = discoveryClient.getInstances(PRIMES).get(0).getUri();
    PrimeNumbers primes = restTemplate.getForObject(uri, PrimeNumbers.class);
    List<Integer> limitedPrimes = primes.getPrimeNumbers().stream().filter(i -> i < limit).collect(Collectors.toList());
    primes.setPrimeNumbers(limitedPrimes);
    return limitedPrimes;
  }

  private String getHostname() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

}
