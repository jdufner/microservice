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

import org.junit.Test;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
public class PrimesControllerTest {

  @Test
  public void testPrimes() {
    PrimesController pc = new PrimesController();
    System.out.println(pc.primes(100));
  }

}