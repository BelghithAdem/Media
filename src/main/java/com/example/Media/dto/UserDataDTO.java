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
  private String photoProfile;

  public UserDataDTO(Long userId, String firstName, String lastName, String photoProfile) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.photoProfile = photoProfile;
  }
  // Getters and setters
}
