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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@RefreshScope
@RestController
@EnableAutoConfiguration
public class HelloWorldController {

  private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

  private final AtomicLong counter = new AtomicLong();
  
  @Autowired
  private PrimesServiceProxy primesServiceProxy;

  @Value("${arbitrary.configuration.value}")
  private String configurationValue = "Hardcoded Value";

  @RequestMapping(path = "/")
  public Greeting helloWorld(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception {
    return doHelloWorld(name, primesServiceProxy.getPrimesSmallerThan(1000));
  }

  public Greeting doHelloWorld(String name, final List<Integer> primes) {
    LOG.error("What is the configuration value? {}", configurationValue);
    Greeting greeting = new Greeting();
    greeting.setId(counter.incrementAndGet());
    greeting.setMessage("Hello " + name + "!");
    greeting.setPrimes(primes);
    greeting.setHostname(getHostname());
    greeting.setConfigurationValue(configurationValue);
    return greeting;
  }

  private String getHostname() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

}
