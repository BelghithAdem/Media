package com.example.Media.Model;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "demande_ajout")
public class DemandeAjout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandeAjout;

    // Autres propriétés spécifiques à DemandeAjout
}