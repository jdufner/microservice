package de.jdufner.microservice.kontoverwaltung.service;

import de.jdufner.microservice.kontoverwaltung.bo.Konto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@Service
public class KontoService {

  private static Logger log = LoggerFactory.getLogger(KontoService.class);

  @Transactional
  public void createKonto(Konto konto) {
    log.info("Erzeuge Konto: {}", konto);
  }

}
