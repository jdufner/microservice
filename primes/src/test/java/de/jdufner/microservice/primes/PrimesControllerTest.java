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

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.ObjectFactory;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class PrimesControllerTest {

  @InjectMocks
  private PrimesController primesController;

  @Mock
  private ObjectFactory<PrimesComputer> primesComputerObjectFactory;

  @Test
  public void testPrimes() {
    // arrange
    Mockito.when(primesComputerObjectFactory.getObject()).thenReturn(new PrimesComputer());

    // act
    PrimesResult primesResult = primesController.primes(100);

    // assert
    Assertions.assertThat(primesResult.getDivisionCounter()).isEqualTo(235);
  }

}