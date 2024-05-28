package com.example.Media.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conversation")
public class Conversation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "conversation_id")
  private Long conversationId;

  @ManyToOne
  @JoinColumn(name = "user1_id")
  private Utilisateur user1;

  @ManyToOne
  @JoinColumn(name = "user2_id")
  private Utilisateur user2;


}

