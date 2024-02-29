package com.example.Media.Repository;

import com.example.Media.Model.Comment;
import com.example.Media.Model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByPost(Post post, Pageable pageable);
}
