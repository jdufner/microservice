package de.jdufner.microservice.hello.world;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@Service
public class PrimesServiceProxy {

  private static final Logger LOG = LoggerFactory.getLogger(PrimesServiceProxy.class);

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  @Autowired
  private RestTemplate restTemplate;

  private String PRIMES = "primes";

  @HystrixCommand(fallbackMethod = "getPrimesUpTo100")
  @Cacheable("primes")
  public List<Integer> getPrimesSmallerThan(int limit) throws Exception {
    URI uri = loadBalancerClient.choose(PRIMES).getUri();
    LOG.info("Getting Primes smaller than {}", limit);
    PrimeNumbers primes = restTemplate.getForObject(uri, PrimeNumbers.class);
    List<Integer> limitedPrimes = primes.getPrimeNumbers().stream().filter(i -> i < limit).collect(Collectors.toList());
    primes.setPrimeNumbers(limitedPrimes);
    return limitedPrimes;
  }

  public List<Integer> getPrimesUpTo100(int noop) {
    return Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
  }


}
