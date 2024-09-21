package com.example.Media.Repository;

<<<<<<< HEAD



import com.example.Media.Model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
=======
import com.example.Media.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
>>>>>>> master

import java.util.List;
import java.util.Optional;

public interface UtilisateurRespository extends JpaRepository<Utilisateur, Long> {
<<<<<<< HEAD
  Optional<Utilisateur> findById(Long username);



    Optional<Utilisateur> findByEmail(String username);

  @Query(value = "SELECT * FROM utilisateur WHERE user_id <> ?1", nativeQuery = true)
  List<Utilisateur> findAllUsersExceptThisUserId(int userId);

  Page<Utilisateur> findUsersByFollowingUsers(Utilisateur user, Pageable pageable);
  Page<Utilisateur> findUsersByFollowerUsers(Utilisateur user, Pageable pageable);



}

=======

  Optional<Utilisateur> findById(Long username);

  Optional<Utilisateur> findByEmail(String username);

  @Query("SELECT u FROM Utilisateur u WHERE u.nom LIKE %:query% OR u.Prenom LIKE %:query%")
  List<Utilisateur> findUsersByNameOrSurname(@Param("query") String query);

  List<Utilisateur> findAllByIdNot(Long userId);

}
>>>>>>> master
