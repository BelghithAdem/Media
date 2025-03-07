package com.example.Media.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // Utilisez Long au lieu de String pour le champ d'identifiant

  @JsonIgnore
  @Column(name = "mot_de_passe")
  private String mdp; // Mot de passe

  @Column(name = "first_name")
  private String nom; // Nom

<<<<<<< HEAD

=======
>>>>>>> master
  @Column(name = "last_name")
  private String Prenom; // Prénom

  private String email; // Adresse email

  // Boolean pour vérifier si le compte est actif

  private boolean actif = false;
  private Integer followerCount;
  private Integer followingCount;
<<<<<<< HEAD

  // Dates de naissance et de création du compte
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
=======
  private boolean mfaEnabled;

  // Dates de naissance et de création du compte
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
>>>>>>> master
  private Date birthDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date joinDate;

  // Ville actuelle
  @Column(length = 128)
  private String currentCity;

  @Column(length = 16)
  private String gender;

<<<<<<< HEAD

=======
  // Ajoutez un champ pour stocker le secret de l'authentification à deux facteurs
  @Column(name = "secret_2fa")
  private String secret2FA;

  // Ajoutez les méthodes pour gérer le secret de l'authentification à deux
  // facteurs
  public String getSecret2FA() {
    return this.secret2FA;
  }

  public void setSecret2FA(String secret2FA) {
    this.secret2FA = secret2FA;
  }

  // Ajoutez une méthode pour activer l'authentification à deux facteurs
  public void set2FAEnabled(boolean enabled) {
    // Vous pouvez ajouter ici la logique pour activer ou désactiver
    // l'authentification à deux facteurs
  }
>>>>>>> master

  // Rôle de l'utilisateur

  @ManyToOne(cascade = CascadeType.ALL)
  private Role role;

  @JsonIgnore
  @ManyToMany
<<<<<<< HEAD
  @JoinTable(
    name = "follow_users",
    joinColumns = @JoinColumn(name = "followed_id"),
    inverseJoinColumns = @JoinColumn(name = "follower_id")
  )
=======
  @JoinTable(name = "follow_users", joinColumns = @JoinColumn(name = "followed_id"), inverseJoinColumns = @JoinColumn(name = "follower_id"))
>>>>>>> master

  private List<Utilisateur> followerUsers = new ArrayList<>();

  @JsonIgnore
  @ManyToMany(mappedBy = "followerUsers")
  private List<Utilisateur> followingUsers = new ArrayList<>();

  // Liste d'amis
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "friend_list_id")
  private FriendList friendList;

  // Liste des posts sauvegardés par l'utilisateur
  @JsonIgnore
  @ManyToMany
  private List<Post> savedBy = new ArrayList<>();

  // Chemin de la photo de profil
  @Column(name = "photo_profile")
  private String photoProfile;

  // Récupère les rôles de l'utilisateur
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.getLibelle()));
  }

  // Récupère le mot de passe de l'utilisateur
  @Override
  public String getPassword() {
    return this.mdp;
  }

  // Récupère l'email de l'utilisateur
  @Override
  public String getUsername() {
    return this.email;
  }

  public Long getUserId() {
    return this.id;
  }

<<<<<<< HEAD
=======
  public String getCurrentCity() {
    return this.currentCity;
  }

  public Date getBirth() {
    return this.birthDate;
  }
>>>>>>> master

  public String getPrenom() {
    return this.Prenom;
  }

<<<<<<< HEAD

=======
>>>>>>> master
  public String getGender() {
    return this.gender;
  }

<<<<<<< HEAD
  // Vérifie si le compte de l'utilisateur n'est pas expiré, verrouillé ou que les informations d'identification ne sont pas expirées
=======
  // Vérifie si le compte de l'utilisateur n'est pas expiré, verrouillé ou que les
  // informations d'identification ne sont pas expirées
>>>>>>> master
  @Override
  public boolean isAccountNonExpired() {
    return this.actif;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.actif;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.actif;
  }

  // Vérifie si le compte de l'utilisateur est activé
  @Override
  public boolean isEnabled() {
    return this.actif;
  }

  // Méthode pour définir le mot de passe manuellement

<<<<<<< HEAD

=======
>>>>>>> master
  // Méthode pour définir le rôle de l'utilisateur
  public void setRole(Role role) {
    this.role = role;
  }

  public void setPrenom(String Prenom) {
    this.Prenom = Prenom;
  }
<<<<<<< HEAD
  public  void setGender(String gender) {
=======

  public void setGender(String gender) {
>>>>>>> master
    this.gender = gender;
  }

  // Méthode pour définir l'identifiant de l'utilisateur
  public void setId(Long id) {
    this.id = id;
  }

  // Getters et setters supplémentaires pour les autres champs de l'utilisateur

<<<<<<< HEAD


  public void setActif(boolean actif) {
        this.actif = actif;
    }


  public void setPassword(String password) {this.mdp=password;}


=======
  public void setActif(boolean actif) {
    this.actif = actif;
  }

  public void setPassword(String password) {
    this.mdp = password;
  }

  public String getSecret() {
    return this.secret2FA;
  }
>>>>>>> master
}
