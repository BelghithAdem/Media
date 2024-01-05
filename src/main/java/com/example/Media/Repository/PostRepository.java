package com.example.Media.Repository;


import com.example.Media.Model.Post;
import com.example.Media.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface PostRepository extends JpaRepository<Post, Long> {

  @Query("SELECT u FROM Utilisateur u WHERE u.id = :userId")
  Optional<Utilisateur> findUserById(@Param("userId") Long userId);



}
