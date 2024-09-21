package com.example.Media.Controller;

import com.example.Media.Model.Avis;
import com.example.Media.Services.AvisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("avis")
@RestController
public class AvisController {
  private final AvisService avisService;

  public AvisController(AvisService avisService) {
    this.avisService = avisService;
  }
  // Ensure that it's AvisService, not AvisServices

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void creer(@RequestBody Avis avis) {

    this.avisService.creer(avis);
  }

  @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Avis> list() {
    return this.avisService.liste();
  }
}
