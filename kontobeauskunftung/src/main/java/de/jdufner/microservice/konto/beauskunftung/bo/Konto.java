package de.jdufner.microservice.konto.beauskunftung.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@Data
public class Konto {

  @Size(max = 10)
  private String nummer;

  @NotNull
  @Size(max = 100)
  private String inhaber;

  private Date anlagedatum;

}
