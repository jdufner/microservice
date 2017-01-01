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
package de.jdufner.microservice.primes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
public abstract class AbstractPrimesComputer {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractPrimesComputer.class);

  protected final PrimesResult primesResult = new PrimesResult();
  protected final List<Integer> primeNumbers = new ArrayList<>();
  protected long divisionCounter = 0;

  protected boolean isDivider(final int divisor, final int dividend) {
    return divisor % dividend == 0;
  }

  public PrimesResult primes(int maxPrimeNumber) {
    computePrimeNumbers(maxPrimeNumber);
    primesResult.setPrimeNumbers(primeNumbers);
    primesResult.setDivisionCounter(divisionCounter);
    return primesResult;
  }

  private void computePrimeNumbers(int maxPrimeNumber) {
    LOG.info("Ermittle alle Primzahlen bis {}.", maxPrimeNumber);
    for (int divisor = 2; divisor < maxPrimeNumber; divisor++) {
      if (isPrimeNumber(divisor)) {
        primeNumbers.add(divisor);
      }
    }
    LOG.info("Für die Berechnung von Primzahlen bis {} waren {} Divisionen nötig.", maxPrimeNumber, divisionCounter);
  }

  private boolean isPrimeNumber(final int primeNumberCandidate) {
    int sqrt = (int) sqrt(primeNumberCandidate);
    return checkPrimeNumberCandidate(primeNumberCandidate, sqrt);
  }

  protected abstract boolean checkPrimeNumberCandidate(final int primeNumberCandidate, final int sqrt);

}
