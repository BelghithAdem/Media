package com.example.Media.Services;


import com.example.Media.Model.Utilisateur;
import com.example.Media.Model.Validation;
import com.example.Media.Repository.ValidationRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Service
public class ValidationService {
    private ValidationRespository validationRepository;
    private NotificationService notificationService;


    public void enregister(Utilisateur utilisateur) {

        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, MINUTES);
        validation.setExpiration(expiration);
        Random random = new Random();
        int randomInteger = random.nextInt(999999999);
        String code = String.format("%06d", randomInteger);

        validation.setCode(code);
        this.validationRepository.save(validation);
      // this.notificationService.envoyer(validation);
    }
    public Validation Lireenfonctionducode(String code){
        return this.validationRepository.findByCode(code).orElseThrow(()-> new RuntimeException("votre code invalide"));

    }
}
