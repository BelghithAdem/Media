package com.example.Media.Repository;




import com.example.Media.Model.Utilisateur;
import com.example.Media.dto.UserDataDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRespository extends JpaRepository<Utilisateur, Long> {
  Optional<Utilisateur> findById(Long username);

  @Query("SELECT new com.example.Media.dto.UserDataDTO(u.id, u.nom, u.Prenom) FROM Utilisateur u")
  List<UserDataDTO> findAllUserNamesAndEmails();
  @Query("SELECT new com.example.Media.dto.UserDataDTO(u.id, u.nom, u.Prenom,u.photoProfile) FROM Utilisateur u WHERE u.nom LIKE %:query% OR u.Prenom LIKE %:query%")
  List<UserDataDTO> findUsersByNameOrSurname(@Param("query") String query);
    Optional<Utilisateur> findByEmail(String username);

  @Query(value = "SELECT * FROM utilisateur WHERE user_id <> ?1", nativeQuery = true)
  List<Utilisateur> findAllUsersExceptThisUserId(int userId);

  Page<Utilisateur> findUsersByFollowingUsers(Utilisateur user, Pageable pageable);
  Page<Utilisateur> findUsersByFollowerUsers(Utilisateur user, Pageable pageable);



}

