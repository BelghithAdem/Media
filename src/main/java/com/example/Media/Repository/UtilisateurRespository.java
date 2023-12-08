package com.example.Media.Repository;



import com.example.Media.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRespository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findById(Long username);
    Optional<Utilisateur> findByEmail(String username);
}

