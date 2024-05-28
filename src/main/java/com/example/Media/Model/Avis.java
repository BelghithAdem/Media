package com.example.Media.Model;






import lombok.*;

import jakarta.persistence.*;




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
        this.utilisateur=utilisateur;
    }
}
