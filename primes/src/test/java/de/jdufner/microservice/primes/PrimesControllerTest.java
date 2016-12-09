package de.jdufner.microservice.primes;

import org.junit.Test;

/**
 * Created by jdufner on 08.12.16.
 */
public class PrimesControllerTest {

  @Test
  public void testPrimes() {
    PrimesController pc = new PrimesController();
    System.out.println(pc.primes());
  }

}