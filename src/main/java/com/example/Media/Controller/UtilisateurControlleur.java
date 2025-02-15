package com.example.Media.Controller;

import com.example.Media.Repository.UtilisateurRespository;
import com.example.Media.Security.JwtService;
import com.example.Media.Services.UtilisateurService;
import com.example.Media.advice.ApiResponse;
import com.example.Media.advice.VerificationRequest;
import com.example.Media.dto.*;
import com.example.Media.Model.Utilisateur;
import com.example.Media.tfa.TwoFactorAuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ComponentScan
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class UtilisateurControlleur {

  private AuthenticationManager authenticationManager;
  private UtilisateurService utilisateurService;
  private JwtService jwtService;
  private UtilisateurRespository utilisateurRespository;
  private final TwoFactorAuthenticationService tfaService;

  @PostMapping(path = "/inscription")
  public ResponseEntity<ApiResponse> inscription(@RequestBody SignupDto signupDto) {
    try {
      log.info("Inscription");

      // Convertir SignupDto en Utilisateur
      Utilisateur utilisateur = new Utilisateur();
      utilisateur.setEmail(signupDto.getEmail());
      utilisateur.setPassword(signupDto.getPassword());
      utilisateur.setNom(signupDto.getNom());
      utilisateur.setPrenom(signupDto.getPrenom());
      utilisateur.setMfaEnabled(signupDto.isMfaEnabled());

      // Appeler la méthode d'inscription du service utilisateur
      utilisateurService.inscription1(utilisateur);

      // Générer et renvoyer le code QR si l'authentification à deux facteurs est
      // activée
      if (signupDto.isMfaEnabled()) {
        String qrCodeUri = tfaService.generateQrCodeImageUri(utilisateur.getSecret2FA());
        // Renvoyer l'URI du code QR dans la réponse
        return new ResponseEntity<>(new ApiResponse("Inscription réussie", qrCodeUri), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(new ApiResponse("Inscription réussie"), HttpStatus.OK);
      }
    } catch (RuntimeException e) {
      return new ResponseEntity<>(
          new ApiResponse(400, "Bad Request", e.getMessage(), "/inscription", "Erreur lors de l'inscription"),
          HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/verify")
  public ResponseEntity<?> verifyCode(@RequestBody VerificationRequest verificationRequest) {
    try {
      // Récupérer l'utilisateur à partir de l'e-mail
      Utilisateur user = utilisateurRespository.findByEmail(verificationRequest.getEmail())
          .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

      // Utiliser le service utilisateur pour vérifier le code OTP
      if (tfaService.isOtpNotValid(user.getSecret(), verificationRequest.getCode())) {
        throw new BadCredentialsException("Code is not correct");
      }

      // Generate JWT response
      Map<String, String> jwtResponse = jwtService.generate(user.getEmail());

      // Prepare the response
      Map<String, Object> response = new HashMap<>();
      response.putAll(jwtResponse);
      response.put("mfaEnabled", user.isMfaEnabled());

      return ResponseEntity.ok(response);

    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserDataDTO>> searchUsers(
      @RequestParam("query") String query) {
    List<Utilisateur> utilisateurs = utilisateurRespository.findUsersByNameOrSurname(query);
    List<UserDataDTO> userDataDTOList = utilisateurs.stream()
        .map(utilisateur -> new UserDataDTO(
            utilisateur.getId(),
            utilisateur.getNom(),
            utilisateur.getPrenom(),
            utilisateur.getPhotoProfile()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(userDataDTOList);
  }

<<<<<<< HEAD

=======
>>>>>>> ec07061a1025971d9936e519837da66384ea741c
  /// "role":{ "libelle":"ADMINISTRATEUR"}

  @PostMapping(path = "activation")
  public ResponseEntity<ApiResponse> activation(@RequestBody Map<String, String> activation) {
    try {
      log.info("Activation");
      this.utilisateurService.activation(activation);
      return new ResponseEntity<>(new ApiResponse("Activation réussie"), HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(
          new ApiResponse(401, "Unauthorized", e.getMessage(), "/activation",
              "Nous n'avons pas pu activer votre compte"),
          HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping(path = "connexion")
<<<<<<< HEAD
  public ResponseEntity<Map<String, String>> connexion(@RequestBody AuthentificationDTO authentificationDTO) {
    Map<String, String> response = new HashMap<>();

    // Vérifier si l'utilisateur existe
    Utilisateur user = utilisateurRespository.findByEmail(authentificationDTO.username())
      .orElse(null);

    if (user == null) {
      response.put("success", "false");
      response.put("reason", "Email non trouvé");
      return ResponseEntity.ok(response);
=======
  public Map<String, String> connexion(@RequestBody AuthentificationDTO authentificationDTO) {
    final Authentication authenticate = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password()));

    // Récupérer l'utilisateur à partir de l'e-mail
    Utilisateur user = utilisateurRespository.findByEmail(authentificationDTO.username())
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    // Créer une carte pour stocker les informations de réponse
    Map<String, String> response = new HashMap<>();

    // Vérifier si l'authentification est réussie et si l'utilisateur a activé la
    // double authentification
    if (authenticate.isAuthenticated() && user.isMfaEnabled()) {
      // Générer le token JWT et retourner avec l'indication que MFA est activé
      response.put("mfaEnabled", "true");
    } else if (authenticate.isAuthenticated()) {
      // Si l'authentification réussit et MFA n'est pas activé, retourner simplement
      // le token JWT
      response.put("mfaEnabled", "false");
      Map<String, String> jwtResponse = jwtService.generate(authentificationDTO.username());
      response.putAll(jwtResponse);
    } else {
      // Si l'authentification échoue, retourner null
      return null;
>>>>>>> ec07061a1025971d9936e519837da66384ea741c
    }

    try {
      Authentication authenticate = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())
      );

      if (authenticate.isAuthenticated()) {
        response.put("success", "true");
        response.put("mfaEnabled", String.valueOf(user.isMfaEnabled()));

        if (!user.isMfaEnabled()) {
          Map<String, String> jwtResponse = jwtService.generate(authentificationDTO.username());
          response.putAll(jwtResponse);
        }
      }
    } catch (BadCredentialsException e) {
      response.put("success", "false");
      response.put("reason", "Mot de passe incorrect");
    }

    return ResponseEntity.ok(response);
  }

<<<<<<< HEAD




=======
>>>>>>> ec07061a1025971d9936e519837da66384ea741c
  @PostMapping("/account/update/info")
  public ResponseEntity<?> updateUserInfo(@RequestParam Long userId,
      @RequestBody @Valid UpdateUserInfoDto updateUserInfoDto) {
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
          HttpStatus.BAD_REQUEST);
    }

  }

  @GetMapping("conversation")
  public ResponseEntity<ApiResponsee> findConversationIdByUser1IdAndUser2Id(@RequestParam("user1") int user1Id,
      @RequestParam("user2") int user2Id) {
    return utilisateurService.findConversationIdByUser1IdAndUser2Id(user1Id, user2Id);
  }

  @GetMapping("/except/{userId}")
  public ResponseEntity<ApiResponsee> findAllUsersExceptThisUserId(@PathVariable int userId) {
    return utilisateurService.findAllUsersExceptThisUserId(userId);
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity<?> getUserById(@PathVariable Long userId) {
    try {
      return utilisateurRespository.findById(userId)
          .map(utilisateur -> new ResponseEntity<>(utilisateur, HttpStatus.OK))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
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
