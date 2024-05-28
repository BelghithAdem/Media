package com.example.Media.advice;

import com.example.Media.Model.Utilisateur;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Utilisateur user;
    private Boolean followedByAuthUser;
}
