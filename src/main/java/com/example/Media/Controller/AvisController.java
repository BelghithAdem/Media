package com.example.Media.Controller;


import com.example.Media.Model.Avis;
import com.example.Media.Services.AvisService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
}
