package com.example.Media.Services;

import com.example.Media.Model.Comment;
import com.example.Media.Model.Post;
import com.example.Media.Model.Utilisateur;
import com.example.Media.Repository.CommentRepository;
<<<<<<< HEAD
import com.example.Media.Repository.PostRepository;
=======
>>>>>>> master
import com.example.Media.Repository.UtilisateurRespository;
import com.example.Media.advice.CommentResponse;
import com.example.Media.advice.InvalidOperationException;
import com.example.Media.advice.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> master
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final UtilisateurRespository utilisateurRespository;

  public Comment getCommentById(Long commentId) {
    return commentRepository.findById(commentId)
<<<<<<< HEAD
      .orElseThrow(CommentNotFoundException::new);
=======
        .orElseThrow(CommentNotFoundException::new);
>>>>>>> master
  }

  public Comment createNewComment(String content, Post post, Long userId) {
    Utilisateur authUser = utilisateurRespository.findById(userId)
<<<<<<< HEAD
      .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
=======
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
>>>>>>> master
    Comment newComment = new Comment();
    newComment.setContent(content);
    newComment.setAuthor(authUser);
    newComment.setPost(post);
    newComment.setLikeCount(0);
    newComment.setDateCreated(new Date());
    newComment.setDateLastModified(new Date());
    return commentRepository.save(newComment);
  }

  public Comment updateComment(Long commentId, String content, Long userId) {
    Utilisateur authUser = utilisateurRespository.findById(userId)
<<<<<<< HEAD
      .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
=======
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
>>>>>>> master
    Comment targetComment = getCommentById(commentId);
    if (targetComment.getAuthor().equals(authUser)) {
      targetComment.setContent(content);
      targetComment.setDateLastModified(new Date());
      return commentRepository.save(targetComment);
    } else {
      throw new InvalidOperationException();
    }
  }

  public void deleteComment(Long commentId, Long userId) {
    Utilisateur authUser = utilisateurRespository.findById(userId)
<<<<<<< HEAD
      .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
=======
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
>>>>>>> master
    Comment targetComment = getCommentById(commentId);
    if (targetComment.getAuthor().equals(authUser)) {
      commentRepository.deleteById(commentId);
    } else {
      throw new InvalidOperationException();
    }
  }

  public Comment likeComment(Long commentId, Long userId) {
    Utilisateur authUser = utilisateurRespository.findById(userId)
<<<<<<< HEAD
      .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
=======
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
>>>>>>> master
    Comment targetComment = getCommentById(commentId);
    if (!targetComment.getLikeList().contains(authUser)) {
      targetComment.setLikeCount(targetComment.getLikeCount() + 1);
      targetComment.getLikeList().add(authUser);
      targetComment.setDateLastModified(new Date());
      return commentRepository.save(targetComment);
    } else {
      throw new InvalidOperationException();
    }
  }

  public Comment unlikeComment(Long commentId, Long userId) {
    Utilisateur authUser = utilisateurRespository.findById(userId)
<<<<<<< HEAD
      .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
=======
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
>>>>>>> master
    Comment targetComment = getCommentById(commentId);
    if (targetComment.getLikeList().contains(authUser)) {
      targetComment.setLikeCount(targetComment.getLikeCount() - 1);
      targetComment.getLikeList().remove(authUser);
      targetComment.setDateLastModified(new Date());
      Comment updatedComment = commentRepository.save(targetComment);

<<<<<<< HEAD
      // Suppression de la notification si l'utilisateur n'est pas l'auteur du commentaire
      if (!targetComment.getAuthor().equals(authUser)) {
        // notificationService.removeNotification(...) ; // Assurez-vous d'avoir le service de notification disponible
=======
      // Suppression de la notification si l'utilisateur n'est pas l'auteur du
      // commentaire
      if (!targetComment.getAuthor().equals(authUser)) {
        // notificationService.removeNotification(...) ; // Assurez-vous d'avoir le
        // service de notification disponible
>>>>>>> master
      }

      return updatedComment;
    } else {
      throw new InvalidOperationException();
    }
  }

  public List<CommentResponse> getPostCommentsPaginate(Post post, Integer page, Integer size, Long userId) {
    Utilisateur authUser = utilisateurRespository.findById(userId)
<<<<<<< HEAD
      .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
    List<Comment> foundCommentList = commentRepository.findByPost(
      post,
      PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated"))
    );
=======
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
    List<Comment> foundCommentList = commentRepository.findByPost(
        post,
        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));
>>>>>>> master

    List<CommentResponse> commentResponseList = new ArrayList<>();
    foundCommentList.forEach(comment -> {
      CommentResponse newCommentResponse = CommentResponse.builder()
<<<<<<< HEAD
        .comment(comment)
        .likedByAuthUser(comment.getLikeList().contains(authUser))
        .build();
=======
          .comment(comment)
          .likedByAuthUser(comment.getLikeList().contains(authUser))
          .build();
>>>>>>> master
      commentResponseList.add(newCommentResponse);
    });

    return commentResponseList;
  }
<<<<<<< HEAD
=======

>>>>>>> master
  public List<Utilisateur> getLikesByCommentPaginate(Comment comment, int page, int size) {
    int start = page * size;
    int end = Math.min(start + size, comment.getLikeList().size());
    return comment.getLikeList().subList(start, end);
  }

}
<<<<<<< HEAD

=======
>>>>>>> master
