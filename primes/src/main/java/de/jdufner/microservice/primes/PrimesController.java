package de.jdufner.microservice.primes;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Math.sqrt;

/**
 * Created by jdufner on 08.12.16.
 */
@RestController
@EnableAutoConfiguration
public class PrimesController {

  @RequestMapping(path = "/")
  public String primes() {
    String primzahlen = "";
    for (int dividend = 2; dividend < 10000; dividend++) {
      boolean istPrimzahl = true;
      for (int divisor = 2; divisor < sqrt(dividend); divisor++)  {
        if (dividend % divisor == 0)  {
          istPrimzahl = false;
          break;
        }
      }
      if (istPrimzahl) {
        if (primzahlen.isEmpty()) {
          primzahlen = String.valueOf(dividend);
        } else {
          primzahlen += ", " + dividend;
        }
      }
    }
    return primzahlen;
   }

}