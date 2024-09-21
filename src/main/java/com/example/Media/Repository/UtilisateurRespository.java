package com.example.Media.Repository;

import com.example.Media.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRespository extends JpaRepository<Utilisateur, Long> {

  Optional<Utilisateur> findById(Long username);

  Optional<Utilisateur> findByEmail(String username);

  @Query("SELECT u FROM Utilisateur u WHERE u.nom LIKE %:query% OR u.Prenom LIKE %:query%")
  List<Utilisateur> findUsersByNameOrSurname(@Param("query") String query);

  List<Utilisateur> findAllByIdNot(Long userId);

}
