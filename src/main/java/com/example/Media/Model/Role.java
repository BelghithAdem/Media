package com.example.Media.Model;


import com.example.Media.TypeDeRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use Long instead of int for the id field

    @Enumerated(EnumType.STRING)
    private TypeDeRole libelle;

    public TypeDeRole getLibelle() {
        return libelle;
    }

    // Méthode setLibelle manuellement
    public void setLibelle(TypeDeRole libelle) {
        this.libelle = libelle;
    }
}