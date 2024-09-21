package com.example.Media.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {
<<<<<<< HEAD
    Long messageId;
  Long senderId;
  Long receiverId;
    String message;
    Date timestamp;
=======
  Long messageId;
  Long senderId;
  Long receiverId;
  String message;
  Date timestamp;
>>>>>>> master
}
