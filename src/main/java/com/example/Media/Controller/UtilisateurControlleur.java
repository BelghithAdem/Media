package com.example.Media.Controller;

import com.example.Media.Security.JwtService;
import com.example.Media.Services.UtilisateurService;
import com.example.Media.advice.ApiResponse;
import com.example.Media.dto.AuthentificationDTO;
import com.example.Media.Model.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@ComponentScan
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurControlleur {

    private AuthenticationManager authenticationManager;
    private UtilisateurService utilisateurService;
    private JwtService jwtService;

  @PostMapping(path = "inscription")
  public ResponseEntity<ApiResponse> inscription(@RequestBody Utilisateur utilisateur) {
    try {
      log.info("Inscription");
      this.utilisateurService.inscription(utilisateur);
      return new ResponseEntity<>(new ApiResponse("Inscription réussie"), HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(
        new ApiResponse(400, "Bad Request", e.getMessage(), "/inscription", "Erreur lors de l'inscription"),
        HttpStatus.BAD_REQUEST
      );
    }
  }

    /// "role":{ "libelle":"ADMINISTRATEUR"}

  @PostMapping(path = "activation")
  public ResponseEntity<ApiResponse> activation(@RequestBody Map<String, String> activation) {
    try {
      log.info("Activation");
      this.utilisateurService.activation(activation);
      return new ResponseEntity<>(new ApiResponse("Activation réussie"), HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(
        new ApiResponse(401, "Unauthorized", e.getMessage(), "/activation", "Nous n'avons pas pu activer votre compte"),
        HttpStatus.UNAUTHORIZED
      );
    }
  }

    @PostMapping(path = "connexion")
    public Map<String, String> connexion(@RequestBody AuthentificationDTO authentificationDTO) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())
        );

        if(authenticate.isAuthenticated()) {
            return this.jwtService.generate(authentificationDTO.username());
        }
        return null;
    }
}
