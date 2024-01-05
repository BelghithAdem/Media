package com.example.Media.Services;

import com.example.Media.Model.Post;
import com.example.Media.Model.Utilisateur;
import com.example.Media.Repository.PostRepository;
import com.example.Media.Repository.UtilisateurRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
  private UtilisateurRespository utilisateurRespository;
  @Autowired
  private PostRepository postRepository;

  public Post createNewPost(Post post, Long userId) throws Exception {
    Optional<Utilisateur> optionalUser = postRepository.findUserById(userId);
    if (optionalUser.isPresent()) {
      Utilisateur user = optionalUser.get();

      Post newPost = new Post();
      newPost.setCaption(post.getCaption());
      newPost.setImage(post.getImage());
      newPost.setCreatedAt(LocalDateTime.now());
      newPost.setVideo(post.getVideo());
      newPost.setUser(user);

      return postRepository.save(newPost);
    } else {
      throw new Exception("User not found");
    }
  }

  public void deletePost(Long postId) throws Exception {
    Optional<Post> optionalPost = postRepository.findById(postId);

    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();

      // Assurez-vous de gérer les relations avec d'autres entités si nécessaire
      // Par exemple, si vous avez des commentaires associés au post, vous pourriez vouloir les supprimer également

      postRepository.delete(post);
    } else {
      throw new Exception("Post not found");
    }
  }

  public List<Post> findAllPosts() {
    return postRepository.findAll();
  }

  public Post findPostById(Long postId) {
    return postRepository.findById(postId)
      .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
  }

  public Post savedPost(Long postId, Long userId) throws Exception {
    Post post = findPostById(postId);
    if (post != null) {
      Optional<Utilisateur> optionalUser = utilisateurRespository.findById(userId);
      if (optionalUser.isPresent()) {
        Utilisateur user = optionalUser.get();

        if (user.getSavedBy().contains(post)) {
          user.getSavedBy().remove(post);
        } else {
          user.getSavedBy().add(post);
        }

        utilisateurRespository.save(user);
        return post;
      } else {
        throw new Exception("User not found");
      }
    } else {
      throw new Exception("Post not found");
    }
  }


  public Post likePost(Long postId, Long userId) throws Exception {
    Post post = findPostById(postId);
    if (post != null) {
      Optional<Utilisateur> optionalUser = postRepository.findUserById(userId);
      if (optionalUser.isPresent()) {
        Utilisateur user = optionalUser.get();

        if (post.getLiked().contains(user)) {
          post.getLiked().remove(user);
        } else {
          post.getLiked().add(user);
        }

        return postRepository.save(post);
      } else {
        throw new Exception("User not found");
      }
    } else {
      throw new Exception("Post not found");
    }
  }
}
