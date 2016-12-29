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
package de.jdufner.microservice.client;

import java.util.List;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
public class Greeting {

  private Long id;
  private String message;
  private List<Integer> primzahlen;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public List<Integer> getPrimzahlen() {
    return primzahlen;
  }

  public void setPrimzahlen(final List<Integer> primzahlen) {
    this.primzahlen = primzahlen;
  }

}
