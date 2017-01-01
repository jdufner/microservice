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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@Component
@Scope("prototype")
public class PrimesOnlyPrimesComputer extends AbstractPrimesComputer {

  private static final Logger LOG = LoggerFactory.getLogger(PrimesOnlyPrimesComputer.class);

  protected boolean checkPrimeNumberCandidate(final int divisor, final int sqrt) {
    for (int primeNumber : primeNumbers) {
      if (primeNumber <= sqrt) {
        divisionCounter++;
        if (isDivider(divisor, primeNumber)) {
          return false;
        }
      } else {
        return true;
      }
    }
    return true;
  }

}
