package com.example.Media.Services;

import com.example.Media.Model.Validation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

<<<<<<< HEAD

=======
>>>>>>> master
@AllArgsConstructor
@Getter
@Setter
@Service
public class NotificationService {

  JavaMailSender javaMailSender; // Inject JavaMailSender

<<<<<<< HEAD


=======
>>>>>>> master
  public void envoyer(Validation validation) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("omarhabli200@gmail.com");
    message.setTo(validation.getUtilisateur().getEmail());
    message.setSubject("Votre code d'activation");
    String texte = String.format("Bonjour %s,\nVotre code d'action est %s.\nA bientôt",
<<<<<<< HEAD
      validation.getUtilisateur().getNom(), validation.getCode());
=======
        validation.getUtilisateur().getNom(), validation.getCode());
>>>>>>> master
    message.setText(texte);

    // Send the email
    javaMailSender.send(message);
  }
}
