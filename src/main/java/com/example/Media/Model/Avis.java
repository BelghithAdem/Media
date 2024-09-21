package com.example.Media.Model;

<<<<<<< HEAD





=======
>>>>>>> master
import lombok.*;

import jakarta.persistence.*;

<<<<<<< HEAD



=======
>>>>>>> master
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String statut;
    @ManyToOne
    private Utilisateur utilisateur;

    public void setUtilisateur(Utilisateur utilisateur) {
<<<<<<< HEAD
        this.utilisateur=utilisateur;
=======
        this.utilisateur = utilisateur;
>>>>>>> master
    }
}
