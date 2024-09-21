package com.example.Media.Model;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "demande_ajout")
<<<<<<< HEAD
public class DemandeAjout  {
=======
public class DemandeAjout {
>>>>>>> master

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandeAjout;

    // Autres propriétés spécifiques à DemandeAjout
}