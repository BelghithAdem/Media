package com.example.Media.Model;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message_joint")
public class MessageJoint  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJoint;

    @Lob
    private String contenuJoint;

    // Autres propriétés spécifiques à MessageJoint
}