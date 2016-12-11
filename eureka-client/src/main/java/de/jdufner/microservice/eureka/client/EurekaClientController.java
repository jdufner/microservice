package de.jdufner.microservice.eureka.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jdufner on 09.12.16.
 */
@RestController
public class EurekaClientController {

  private final static Logger LOG = LoggerFactory.getLogger(EurekaClientController.class);

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/service-instances/{applicationName}")
  @HystrixCommand
  public List<ServiceInstance> serviceInstancesByApplicationName(
      @PathVariable String applicationName) {
    for (String service : discoveryClient.getServices()) {
      LOG.info(service);
      for (ServiceInstance instance : discoveryClient.getInstances(service)) {
        LOG.info("URI={}, Host={}, Port={}, ServiceId={}", instance.getUri(), instance.getHost(), instance.getPort(), instance.getServiceId());
      }
    }
    return discoveryClient.getInstances(applicationName);
  }

}