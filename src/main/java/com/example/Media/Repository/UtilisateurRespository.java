package com.example.Media.Repository;




import com.example.Media.Model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRespository extends JpaRepository<Utilisateur, Long> {
  Optional<Utilisateur> findById(Long username);



    Optional<Utilisateur> findByEmail(String username);

  @Query(value = "SELECT * FROM utilisateur WHERE user_id <> ?1", nativeQuery = true)
  List<Utilisateur> findAllUsersExceptThisUserId(int userId);

  Page<Utilisateur> findUsersByFollowingUsers(Utilisateur user, Pageable pageable);
  Page<Utilisateur> findUsersByFollowerUsers(Utilisateur user, Pageable pageable);



}

