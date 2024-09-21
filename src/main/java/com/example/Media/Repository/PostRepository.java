package com.example.Media.Repository;

<<<<<<< HEAD

=======
>>>>>>> master
import com.example.Media.Model.Post;
import com.example.Media.Model.Utilisateur;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface PostRepository extends JpaRepository<Post, Long> {


  List<Post> findPostsByUser(Utilisateur user, Pageable pageable);
  List<Post> findPostsByUser_IdIn(List<Long> userIds, Pageable pageable);

  List<Post> findPostsBySharedPost(Post post, Pageable pageable);


=======

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findPostsByUser(Utilisateur user, Pageable pageable);

  List<Post> findPostsByUser_IdIn(List<Long> userIds, Pageable pageable);

  List<Post> findByUserId(Long userId);

  List<Post> findPostsBySharedPost(Post post, Pageable pageable);

>>>>>>> master
}
