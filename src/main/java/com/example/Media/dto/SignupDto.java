package com.example.Media.dto;


import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
  private String email;
  private String password;
  private String nom;
  private String prenom;
  private boolean mfaEnabled;

}
