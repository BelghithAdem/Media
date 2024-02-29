package com.example.Media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDTO {
  private Long userId;
  private String firstName;
  private String lastName;
  private String email;

  // Getters and setters
}
