package com.example.Media.dto;

<<<<<<< HEAD

import jakarta.validation.constraints.Size;
import lombok.*;


=======
import jakarta.validation.constraints.Size;
import lombok.*;

>>>>>>> master
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
<<<<<<< HEAD
=======
  private boolean mfaEnabled;

>>>>>>> master
}
