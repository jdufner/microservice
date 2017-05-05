package de.jdufner.microservice.konto.beauskunftung.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jdufner on 24.04.17.
 */
@Component
public class Receiver {

  private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

  public void receiveMessage(String message) {
    LOG.info(message);
  }

}
