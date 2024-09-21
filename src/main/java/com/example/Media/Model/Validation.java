package com.example.Media.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "validation")
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant creation;
    private Instant expiration;
    private Instant activation;
    private String code;

    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }

    public void setCreation(Instant instant) {
        this.creation = instant;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setCode(String code) {
<<<<<<< HEAD
        this.code=code;
    }
=======
        this.code = code;
    }

>>>>>>> master
    // Getter methods
    public Instant getCreation() {
        return creation;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public Instant getActivation() {
        return activation;
    }

    public String getCode() {
        return code;
    }
<<<<<<< HEAD
=======

>>>>>>> master
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    // Ajoutez d'autres méthodes setter si nécessaire
}
