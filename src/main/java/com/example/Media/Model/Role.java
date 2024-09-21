package com.example.Media.Model;

<<<<<<< HEAD

=======
>>>>>>> master
import com.example.Media.TypeDeRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

<<<<<<< HEAD

=======
>>>>>>> master
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