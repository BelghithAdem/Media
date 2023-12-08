package com.example.Media.Model;
import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "msg_texte")
public class MsgTexte  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTexte;

    @Lob
    private String contenuTexte;

    // Autres propriétés spécifiques à MsgTexte
}