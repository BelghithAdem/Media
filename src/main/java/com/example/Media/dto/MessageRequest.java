package com.example.Media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
<<<<<<< HEAD
  Integer  conversationId;
  Integer  senderId;
  Integer  receiverId;
    String message;
    LocalDateTime timestamp;
=======
  Integer conversationId;
  Integer senderId;
  Integer receiverId;
  String message;
  LocalDateTime timestamp;
>>>>>>> master
}
