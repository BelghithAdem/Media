package com.example.Media.Controller;

import com.example.Media.Security.JwtService;
import com.example.Media.Services.UtilisateurService;
import com.example.Media.advice.ApiResponse;
import com.example.Media.dto.*;
import com.example.Media.Model.Utilisateur;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
  public ResponseEntity<ApiResponse> inscription(@RequestBody SignupDto signupDto) {
    try {
      log.info("Inscription");

      // Convertir SignupDto en Utilisateur
      Utilisateur utilisateur = new Utilisateur();
      utilisateur.setEmail(signupDto.getEmail());
      utilisateur.setPassword(signupDto.getPassword());
      utilisateur.setNom(signupDto.getNom());
      utilisateur.setPrenom(signupDto.getPrenom());

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
  @PostMapping("/account/update/info")
  public ResponseEntity<?> updateUserInfo(@RequestParam Long userId, @RequestBody @Valid UpdateUserInfoDto updateUserInfoDto) {
    Utilisateur updatedUser = utilisateurService.updateUserInfo(userId, updateUserInfoDto);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }
  @PostMapping("/account/follow/{userId}")
  public ResponseEntity<?> followUser(@PathVariable("userId") Long userId) {
    try {
      utilisateurService.followUser(userId); // Appel de la méthode sur l'instance utilisateurService
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/account/unfollow/{userId}")
  public ResponseEntity<?> unfollowUser(@PathVariable("userId") Long userId) {
    try {
      utilisateurService.unfollowUser(userId); // Appel de la méthode sur l'instance utilisateurService
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/users/{userId}/following")
  public ResponseEntity<List<Utilisateur>> getUserFollowingUsers(@PathVariable Long userId) {
    List<Utilisateur> followingUsers = utilisateurService.getUserFollowingUsers(userId);
    return new ResponseEntity<>(followingUsers, HttpStatus.OK);
  }

  @GetMapping("/users/{userId}/followers")
  public ResponseEntity<List<Utilisateur>> getUserFollowerUsers(@PathVariable Long userId) {
    List<Utilisateur> followerUsers = utilisateurService.getUserFollowerUsers(userId);
    return new ResponseEntity<>(followerUsers, HttpStatus.OK);
  }

  @PostMapping(path = "utilisateurs/{userId}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ApiResponse> ajouterPhotoProfil(
    @PathVariable Long userId,
    @RequestParam("photo") MultipartFile photo) {

    try {
      utilisateurService.ajouterPhotoProfil(userId, photo);
      return new ResponseEntity<>(new ApiResponse("Photo de profil ajoutée avec succès"), HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(
        new ApiResponse(400, "Bad Request", e.getMessage(), "/utilisateurs/" + userId + "/photo",
          "Erreur lors de l'ajout de la photo de profil"),
        HttpStatus.BAD_REQUEST
      );
    }




  }


  @GetMapping("conversation")
  public ResponseEntity<ApiResponsee> findConversationIdByUser1IdAndUser2Id(@RequestParam("user1") int user1Id, @RequestParam("user2") int user2Id) {
    return utilisateurService.findConversationIdByUser1IdAndUser2Id(user1Id, user2Id);
  }

  @GetMapping("/except/{userId}")
  public ResponseEntity<ApiResponsee> findAllUsersExceptThisUserId(@PathVariable int userId) {
    return utilisateurService.findAllUsersExceptThisUserId(userId);
  }

  @GetMapping("/all")
  public ResponseEntity<ApiResponsee> findAllUsers() {
    return utilisateurService.findAllUsers();
  }
  @PostMapping("/login")
  public ResponseEntity<ApiResponsee> login(@RequestBody LoginRequest loginRequest) {
    return utilisateurService.findUserByEmail(loginRequest.getEmail());
  }
}
