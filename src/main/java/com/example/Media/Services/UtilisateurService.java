package com.example.Media.Services;

import com.example.Media.Model.Conversation;
import com.example.Media.Repository.ConversationRepository;
<<<<<<< HEAD
import com.example.Media.dto.UserDataDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

=======
import com.example.Media.Security.JwtService;
import com.example.Media.advice.VerificationRequest;
import com.example.Media.dto.AuthenticationResponse;
import com.example.Media.dto.UserDataDTO;
import com.example.Media.tfa.TwoFactorAuthenticationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> master
import com.example.Media.Model.Role;
import com.example.Media.Model.Utilisateur;
import com.example.Media.Model.Validation;
import com.example.Media.Repository.UtilisateurRespository;
import com.example.Media.TypeDeRole;
import com.example.Media.advice.InvalidOperationException;
import com.example.Media.advice.UserNotFoundException;
<<<<<<< HEAD
import com.example.Media.advice.UserResponse;
import com.example.Media.dto.ApiResponsee;
import com.example.Media.dto.UpdateUserInfoDto;
import jakarta.transaction.Transactional;
import jdk.jshell.execution.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
=======
import com.example.Media.dto.ApiResponsee;
import com.example.Media.dto.UpdateUserInfoDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
>>>>>>> master
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD

import java.io.IOException;
import java.nio.file.Path;
=======
>>>>>>> master
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
<<<<<<< HEAD
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UtilisateurService implements UserDetailsService {
  @Autowired
    private UtilisateurRespository utilisateurRespository;
    private ValidationService validationService;
  private final ConversationRepository conversationRepository;

    private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private FileStorageService fileStorageService;  // Ajout de l'injection de dépendance

  public Utilisateur getUserById(Long userId) {
    return utilisateurRespository.findById(userId).orElseThrow(UserNotFoundException::new);
  }
  public void inscription(Utilisateur utilisateur) {
    if (!utilisateur.getUsername().contains("@")) {
      throw new RuntimeException("Votre email est invalide");
    }
    if (!utilisateur.getUsername().contains(".")) {
      throw new RuntimeException("Votre email est invalide");
    }

    // Recherche de l'utilisateur par e-mail
    Optional<Utilisateur> utilisateurOptionel = utilisateurRespository.findByEmail(utilisateur.getUsername());

    if (utilisateurOptionel.isPresent()) {
      throw new RuntimeException("Votre email est déjà utilisé");
    }

    String mdpCrypte = passwordEncoder.encode(utilisateur.getPassword());
    utilisateur.setPassword(mdpCrypte);

=======

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UtilisateurService implements UserDetailsService, UtilisateurServiceInterface {
  private final UtilisateurRespository utilisateurRespository; // Repository for User entity
  private final ValidationService validationService; // Service for validation
  private final TwoFactorAuthenticationService tfaService; // Service for two-factor authentication
  private final ConversationRepository conversationRepository; // Repository for Conversation entity
  private final JwtService jwtService; // Service for JWT token operations
  private final BCryptPasswordEncoder passwordEncoder; // Encoder for password hashing
  private final FileStorageService fileStorageService; // Service for file storage

  // Method to get a user by ID
  public Utilisateur getUserById(Long userId) {
    return utilisateurRespository.findById(userId).orElseThrow(UserNotFoundException::new);
  }

  // Method for user registration
  public void inscription1(Utilisateur utilisateur) {
    // Check if email is valid
    if (!utilisateur.getUsername().contains("@") || !utilisateur.getUsername().contains(".")) {
      throw new RuntimeException("Votre email est invalide");
    }

    // Check if user with email already exists
    if (utilisateurRespository.findByEmail(utilisateur.getUsername()).isPresent()) {
      throw new RuntimeException("Votre email est déjà utilisé");
    }

    // Generate 2FA secret if enabled
    if (utilisateur.isMfaEnabled()) {
      utilisateur.setSecret2FA(tfaService.generateNewSecret());
    }

    // Encrypt password
    String hashedPassword = passwordEncoder.encode(utilisateur.getPassword());
    utilisateur.setPassword(hashedPassword);

    // Set user role
>>>>>>> master
    final Role roleUtilisateur = new Role();
    roleUtilisateur.setLibelle(TypeDeRole.UTILISATEUR);
    if (utilisateur.getRole() != null && utilisateur.getRole().getLibelle().equals(TypeDeRole.ADMINISTRATEUR)) {
      roleUtilisateur.setLibelle(TypeDeRole.ADMINISTRATEUR);
      utilisateur.setActif(true);
    }
    utilisateur.setRole(roleUtilisateur);

<<<<<<< HEAD
    // Définir le nombre de followers et following à 0
    utilisateur.setFollowerCount(0);
    utilisateur.setFollowingCount(0);

    // Définir la date de début sur la date actuelle
    utilisateur.setJoinDate(new Date());

    utilisateur = utilisateurRespository.save(utilisateur);
=======
    // Set follower and following counts to 0
    utilisateur.setFollowerCount(0);
    utilisateur.setFollowingCount(0);

    // Set join date to current date
    utilisateur.setJoinDate(new Date());
    utilisateur.setBirthDate(new Date(0));
    utilisateur.setCurrentCity("paris (par default)");

    utilisateur = utilisateurRespository.save(utilisateur);

    // Register user for validation if role is UTILISATEUR
>>>>>>> master
    if (roleUtilisateur.getLibelle().equals(TypeDeRole.UTILISATEUR)) {
      validationService.enregister(utilisateur);
    }
  }

<<<<<<< HEAD

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.Lireenfonctionducode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpiration())) {
            throw new RuntimeException("Votre code a expiré");
        }

        Utilisateur utilisateurActiver = this.utilisateurRespository.findById(validation.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateurActiver.setActif(true);
        this.utilisateurRespository.save(utilisateurActiver);
    }



  @Override
    public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRespository
                .findByEmail(username)
                .orElseThrow(() -> new  UsernameNotFoundException("Aucun utilisateur ne corespond à cet identifiant"));
    }
  public void ajouterPhotoProfil(Long userId, MultipartFile photo) {
    Utilisateur utilisateur = utilisateurRespository.findById(userId)
      .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    try {
      String fileName = "post_"  + "_" + photo.getOriginalFilename();
      fileStorageService.storeFile(photo, fileName);
      utilisateur.setPhotoProfile(fileName); // Définition du nom de fichier dans l'attribut photoProfile de l'utilisateur
      utilisateurRespository.save(utilisateur); // Enregistrement de l'utilisateur mis à jour
=======
  // Method to activate user
  public void activation(Map<String, String> activation) {
    // Read validation based on code
    Validation validation = this.validationService.Lireenfonctionducode(activation.get("code"));
    if (Instant.now().isAfter(validation.getExpiration())) {
      throw new RuntimeException("Votre code a expiré");
    }

    // Activate user
    Utilisateur utilisateurActiver = this.utilisateurRespository.findById(validation.getUtilisateur().getId())
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    utilisateurActiver.setActif(true);
    this.utilisateurRespository.save(utilisateurActiver);
  }

  // Method to verify two-factor authentication code
  public AuthenticationResponse verifyCode(VerificationRequest verificationRequest) {
    Utilisateur user = utilisateurRespository.findByEmail(verificationRequest.getEmail())
        .orElseThrow(
            () -> new EntityNotFoundException(String.format("No user found with %S", verificationRequest.getEmail())));

    if (tfaService.isOtpNotValid(user.getSecret(), verificationRequest.getCode())) {
      throw new BadCredentialsException("Code is not correct");
    }

    return AuthenticationResponse.builder()
        .mfaEnabled(user.isMfaEnabled())
        .build();
  }

  // Method to load user by username
  @Override
  public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.utilisateurRespository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne corespond à cet identifiant"));
  }

  // Method to add profile photo for user
  public void ajouterPhotoProfil(Long userId, MultipartFile photo) {
    Utilisateur utilisateur = utilisateurRespository.findById(userId)
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    try {
      String fileName = "post_" + "_" + photo.getOriginalFilename();
      fileStorageService.storeFile(photo, fileName);
      utilisateur.setPhotoProfile(fileName);
      utilisateurRespository.save(utilisateur);
>>>>>>> master
    } catch (Exception ex) {
      throw new RuntimeException("Erreur lors de la sauvegarde de la photo de profil", ex);
    }
  }

<<<<<<< HEAD

  public Utilisateur updateUserInfo(Long userId, UpdateUserInfoDto updateUserInfoDto) {
    // Vérification si l'utilisateur existe
    Optional<Utilisateur> optionalUser = utilisateurRespository.findById(userId);
    if (optionalUser.isPresent()) {
      Utilisateur utilisateur = optionalUser.get();
      // Mise à jour des informations de l'utilisateur
=======
  // Method to update user information
  public Utilisateur updateUserInfo(Long userId, UpdateUserInfoDto updateUserInfoDto) {
    Optional<Utilisateur> optionalUser = utilisateurRespository.findById(userId);
    if (optionalUser.isPresent()) {
      Utilisateur utilisateur = optionalUser.get();
>>>>>>> master
      utilisateur.setNom(updateUserInfoDto.getNom());
      utilisateur.setPrenom(updateUserInfoDto.getPrenom());
      utilisateur.setCurrentCity(updateUserInfoDto.getCurrentCity());
      utilisateur.setBirthDate(updateUserInfoDto.getBirthDate());
      utilisateur.setGender(updateUserInfoDto.getGender());
<<<<<<< HEAD
      // Enregistrement des modifications dans la base de données
=======
>>>>>>> master
      return utilisateurRespository.save(utilisateur);
    } else {
      throw new UserNotFoundException("Utilisateur non trouvé avec l'ID: " + userId);
    }
  }
<<<<<<< HEAD
=======

  // Method to get users followed by a user
>>>>>>> master
  public List<Utilisateur> getUserFollowingUsers(Long userId) {
    Utilisateur utilisateur = getUserById(userId);
    return utilisateur.getFollowingUsers();
  }

<<<<<<< HEAD
  // Méthode pour récupérer les utilisateurs qui suivent un utilisateur donné
=======
  // Method to get users following a user
>>>>>>> master
  public List<Utilisateur> getUserFollowerUsers(Long userId) {
    Utilisateur utilisateur = getUserById(userId);
    return utilisateur.getFollowerUsers();
  }

<<<<<<< HEAD
  @Transactional // Assurez-vous que les opérations sont effectuées dans une transaction
  public void followUser(Long userId) {
    Utilisateur authUser = getAuthenticatedUser();
    Utilisateur userToFollow = getUserById(userId); // Assurez-vous que l'utilisateur à suivre est chargé correctement
    if (!authUser.getId().equals(userId)) {
      authUser.getFollowingUsers().add(userToFollow);
      authUser.setFollowingCount(authUser.getFollowingCount() + 1);
      userToFollow.getFollowerUsers().add(authUser);
      userToFollow.setFollowerCount(userToFollow.getFollowerCount() + 1);
      utilisateurRespository.save(userToFollow); // Enregistrer les modifications
      utilisateurRespository.save(authUser); // Enregistrer les modifications
=======
  // Method to follow a user
  @Transactional
  public void followUser(Long userId) {
    Utilisateur authUser = getAuthenticatedUser();
    Utilisateur userToFollow = getUserById(userId);
    if (!authUser.getId().equals(userId)) {
      if (!authUser.getFollowingUsers().contains(userToFollow)) {
        authUser.getFollowingUsers().add(userToFollow);
        authUser.setFollowingCount(authUser.getFollowingCount() + 1);
      }
      if (!userToFollow.getFollowerUsers().contains(authUser)) {
        userToFollow.getFollowerUsers().add(authUser);
        userToFollow.setFollowerCount(userToFollow.getFollowerCount() + 1);
      }
      utilisateurRespository.save(userToFollow);
      utilisateurRespository.save(authUser);
>>>>>>> master
    } else {
      throw new InvalidOperationException();
    }
  }

<<<<<<< HEAD
  @Transactional // Assurez-vous que les opérations sont effectuées dans une transaction
  public void unfollowUser(Long userId) {
    Utilisateur authUser = getAuthenticatedUser();
    Utilisateur userToUnfollow = getUserById(userId); // Assurez-vous que l'utilisateur à ne plus suivre est chargé correctement
    if (!authUser.getId().equals(userId)) {
      authUser.getFollowingUsers().remove(userToUnfollow);
      authUser.setFollowingCount(authUser.getFollowingCount() - 1);
      userToUnfollow.getFollowerUsers().remove(authUser);
      userToUnfollow.setFollowerCount(userToUnfollow.getFollowerCount() - 1);
      utilisateurRespository.save(userToUnfollow); // Enregistrer les modifications
      utilisateurRespository.save(authUser); // Enregistrer les modifications
=======
  @Transactional
  public void unfollowUser(Long userId) {
    Utilisateur authUser = getAuthenticatedUser();
    Utilisateur userToUnfollow = getUserById(userId);
    if (!authUser.getId().equals(userId)) {
      if (authUser.getFollowingUsers().contains(userToUnfollow)) {
        authUser.getFollowingUsers().remove(userToUnfollow);
        authUser.setFollowingCount(authUser.getFollowingCount() - 1);
      }
      if (userToUnfollow.getFollowerUsers().contains(authUser)) {
        userToUnfollow.getFollowerUsers().remove(authUser);
        userToUnfollow.setFollowerCount(userToUnfollow.getFollowerCount() - 1);
      }
      utilisateurRespository.save(userToUnfollow);
      utilisateurRespository.save(authUser);
>>>>>>> master
    } else {
      throw new InvalidOperationException();
    }
  }
<<<<<<< HEAD
  private Utilisateur getAuthenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return loadUserByUsername(userDetails.getUsername());
=======

  private Utilisateur getAuthenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = ((UserDetails) authentication.getPrincipal()).getUsername();
    return utilisateurRespository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond à cet identifiant"));
>>>>>>> master
  }

  public ResponseEntity<ApiResponsee> findConversationIdByUser1IdAndUser2Id(int user1Id, int user2Id) {
    Long conversationId;
    Optional<Utilisateur> user1 = utilisateurRespository.findById((long) user1Id);
    Optional<Utilisateur> user2 = utilisateurRespository.findById((long) user2Id);
    if (user1.isEmpty() || user2.isEmpty()) {
      return ResponseEntity.ok()
<<<<<<< HEAD
        .body(ApiResponsee.builder()
          .statusCode(200)
          .status("Failed")
          .reason("User not found")
          .data(null)
          .build());
    }

    Optional<Conversation> existingConversation = conversationRepository.findConversationByUsers(user1.get(), user2.get());
=======
          .body(ApiResponsee.builder()
              .statusCode(200)
              .status("Failed")
              .reason("User not found")
              .data(null)
              .build());
    }

    Optional<Conversation> existingConversation = conversationRepository.findConversationByUsers(user1.get(),
        user2.get());
>>>>>>> master
    if (existingConversation.isPresent()) {
      conversationId = existingConversation.get().getConversationId();
    } else {
      Conversation newConversation = new Conversation();
      newConversation.setUser1(user1.get());
      newConversation.setUser2(user2.get());
      Conversation savedConversation = conversationRepository.save(newConversation);
      conversationId = savedConversation.getConversationId();
    }

    return ResponseEntity.ok()
<<<<<<< HEAD
      .body(ApiResponsee.builder()
        .statusCode(200)
        .status("Success")
        .reason("OK")
        .data(conversationId)
        .build());
  }
  public ResponseEntity<ApiResponsee> findAllUsersExceptThisUserId(int userId) {
    List<Utilisateur> utilisateurs = utilisateurRespository.findAllUsersExceptThisUserId(userId);
    List<UserDataDTO> userDataDTOList = utilisateurs.stream()
      .map(this::convertToUserDataDTO)
      .collect(Collectors.toList());

    return new ResponseEntity<>(
      ApiResponsee.builder()
        .statusCode(200)
        .status("Success")
        .reason("OK")
        .data(userDataDTOList)
        .build(),
      HttpStatus.OK
    );
=======
        .body(ApiResponsee.builder()
            .statusCode(200)
            .status("Success")
            .reason("OK")
            .data(conversationId)
            .build());
  }

  public ResponseEntity<ApiResponsee> findAllUsersExceptThisUserId(int userId) {
    List<Utilisateur> utilisateurs = utilisateurRespository.findAllByIdNot((long) userId);
    List<UserDataDTO> userDataDTOList = utilisateurs.stream()
        .map(this::convertToUserDataDTO)
        .collect(Collectors.toList());

    return new ResponseEntity<>(
        ApiResponsee.builder()
            .statusCode(200)
            .status("Success")
            .reason("OK")
            .data(userDataDTOList)
            .build(),
        HttpStatus.OK);
>>>>>>> master
  }

  private UserDataDTO convertToUserDataDTO(Utilisateur utilisateur) {
    UserDataDTO userDataDTO = new UserDataDTO();
    userDataDTO.setUserId(utilisateur.getId());
    userDataDTO.setFirstName(utilisateur.getPrenom());
    userDataDTO.setLastName(utilisateur.getNom());
    userDataDTO.setEmail(utilisateur.getEmail());
    return userDataDTO;
  }

  public ResponseEntity<ApiResponsee> findAllUsers() {
    List<Utilisateur> list = utilisateurRespository.findAll();
    return new ResponseEntity<>(
<<<<<<< HEAD
      ApiResponsee.builder()
        .statusCode(200)
        .status("Success")
        .reason("OK")
        .data(list)
        .build(),
      HttpStatus.OK
    );
=======
        ApiResponsee.builder()
            .statusCode(200)
            .status("Success")
            .reason("OK")
            .data(list)
            .build(),
        HttpStatus.OK);
>>>>>>> master
  }

  public ResponseEntity<ApiResponsee> findUserByEmail(String email) {
    Optional<Utilisateur> userOptional = utilisateurRespository.findByEmail(email);
    if (userOptional.isPresent()) {
      Utilisateur user = userOptional.get();
      UserDataDTO userDataDTO = new UserDataDTO();
      userDataDTO.setUserId(user.getId());
      userDataDTO.setFirstName(user.getPrenom());
      userDataDTO.setLastName(user.getNom());
      userDataDTO.setEmail(user.getEmail());

      return new ResponseEntity<>(
<<<<<<< HEAD
        ApiResponsee.builder()
          .statusCode(200)
          .status("Success")
          .reason("OK")
          .data(userDataDTO)
          .build(),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        ApiResponsee.builder()
          .statusCode(200)
          .status("Failed")
          .reason("User not found")
          .data(null)
          .build(),
        HttpStatus.OK
      );
    }
  }


}




=======
          ApiResponsee.builder()
              .statusCode(200)
              .status("Success")
              .reason("OK")
              .data(userDataDTO)
              .build(),
          HttpStatus.OK);
    } else {
      return new ResponseEntity<>(
          ApiResponsee.builder()
              .statusCode(200)
              .status("Failed")
              .reason("User not found")
              .data(null)
              .build(),
          HttpStatus.OK);
    }
  }

}
>>>>>>> master
