package com.example.Media.Services;

import com.example.Media.Model.Post;
import com.example.Media.Model.Utilisateur;
import com.example.Media.Repository.PostRepository;
import com.example.Media.Repository.UtilisateurRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

  @Autowired
  private UtilisateurRespository utilisateurRespository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private FileStorageService fileStorageService;  // Ajout de l'injection de dépendance

  public Post createNewPost(String caption, Long userId, MultipartFile mediaFile) throws Exception {
    Optional<Utilisateur> optionalUser = utilisateurRespository.findById(userId);
    if (optionalUser.isPresent()) {
      Utilisateur user = optionalUser.get();

      Post newPost = new Post();
      newPost.setCaption(caption);
      newPost.setCreatedAt(LocalDateTime.now());
      newPost.setUser(user);

      // Gérer le fichier média
      if (mediaFile != null && !mediaFile.isEmpty()) {
        String fileName = "post_" + newPost.getId() + "_" + mediaFile.getOriginalFilename();
        fileStorageService.storeFile(mediaFile, fileName);
        newPost.setImageFileName(fileName);  // Utiliser le nom du fichier au lieu des données binaires
      } else {
        throw new Exception("Le fichier média est requis");
      }

      return postRepository.save(newPost);
    } else {
      throw new Exception("Utilisateur non trouvé");
    }
  }
  public byte[] getImageData(String imageFileName) throws IOException {
    Path imagePath = fileStorageService.getFileStorageLocation().resolve(imageFileName).normalize();
    return Files.readAllBytes(imagePath);
  }

  public byte[] getVideoData(String videoFileName) throws IOException {
    Path videoPath = fileStorageService.getFileStorageLocation().resolve(videoFileName).normalize();
    return Files.readAllBytes(videoPath);
  }
  public byte[] getImageDataByName(String imageFileName) throws IOException {
    try {
      Path imagePath = fileStorageService.getFileStorageLocation().resolve(imageFileName).normalize();
      return Files.readAllBytes(imagePath);
    } catch (IOException e) {
      throw new IOException("Erreur lors de la récupération des données de l'image à partir du nom de fichier", e);
    }
  }


  public void deletePost(Long postId) throws Exception {
    Optional<Post> optionalPost = postRepository.findById(postId);

    if (optionalPost.isPresent()) {
      Post post = optionalPost.get();

      // Assurez-vous de gérer les relations avec d'autres entités si nécessaire
      // Par exemple, si vous avez des commentaires associés au post, vous pourriez vouloir les supprimer également

      fileStorageService.deleteFile(post.getImageFileName());  // Supprimer le fichier associé
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
      Optional<Utilisateur> optionalUser = utilisateurRespository.findById(userId);
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
