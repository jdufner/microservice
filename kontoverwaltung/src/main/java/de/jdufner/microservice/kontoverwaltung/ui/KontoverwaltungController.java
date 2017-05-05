package de.jdufner.microservice.kontoverwaltung.ui;

import de.jdufner.microservice.kontoverwaltung.bo.Konto;
import de.jdufner.microservice.kontoverwaltung.service.KontoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by jdufner on 03.05.17.
 */
@Controller
public class KontoverwaltungController {

  private KontoService kontoService;

  public KontoverwaltungController(KontoService kontoService) {
    this.kontoService = kontoService;
  }

  @GetMapping("/overview")
  public String overview(Model model) {
    return "overview";
  }

  @GetMapping("/create")
  public String erzeuge(Model model) {
    model.addAttribute("konto", new Konto());
    return "create";
  }

  @PostMapping("/create")
  public String erzeuge(@ModelAttribute Konto konto) {
    kontoService.createKonto(konto);
    return "overview";
  }

}
