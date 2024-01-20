package com.example.Media.Controller;

import com.example.Media.Model.Post;

import com.example.Media.Services.PostService;
import com.example.Media.advice.ApiResponse;
import com.example.Media.dto.PostResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

// Controller

@RestController
@RequestMapping("/api/posts")
public class PostController {

  @Autowired
  private PostService postService;

  @PostMapping("/user/{userId}")
  public ResponseEntity<?> createPost(
    @RequestParam("caption") String caption,
    @RequestParam("mediaFile") MultipartFile mediaFile,
    @PathVariable Long userId) {

    try {
      Post createdPost = postService.createNewPost(caption, userId, mediaFile);
      return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    } catch (Exception e) {
      ApiResponse res = new ApiResponse(e.getMessage());
      return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/image/{fileName}")
  public ResponseEntity<Resource> serveImageByFileName(@PathVariable String fileName) {
    try {
      // Utilisez le service pour récupérer les données de l'image par le nom de fichier
      byte[] imageData = postService.getImageDataByName(fileName);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_JPEG);

      return new ResponseEntity<>(new ByteArrayResource(imageData), headers, HttpStatus.OK);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }


  @GetMapping("/video/{postId}")
  public ResponseEntity<Resource> serveVideo(@PathVariable Long postId) {
    try {
      Post post = postService.findPostById(postId);

      if (post != null && post.getVideoFileName() != null) {
        byte[] videoData = postService.getVideoData(post.getVideoFileName());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(new ByteArrayResource(videoData), headers, HttpStatus.OK);
      } else {
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  @GetMapping("/all")
  public ResponseEntity<List<Post>> getAllPosts() {
    try {
      List<Post> posts = postService.findAllPosts();
      return new ResponseEntity<>(posts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{postId}/user/{userId}")
  public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId, @PathVariable Long userId) {
    try {
      postService.deletePost(postId);
      ApiResponse res = new ApiResponse("Post deleted successfully");
      return new ResponseEntity<>(res, HttpStatus.OK);
    } catch (Exception e) {
      ApiResponse res = new ApiResponse(e.getMessage());
      return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{postId}")
  public ResponseEntity<Post> findPostById(@PathVariable Long postId) {
    try {
      Post post = postService.findPostById(postId);
      return new ResponseEntity<>(post, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping
  public ResponseEntity<List<Post>> findAllPosts() {
    List<Post> posts = postService.findAllPosts();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }


  @PutMapping("/{postId}/user/{userId}/save")
  public ResponseEntity<Post> savePost(@PathVariable Long postId, @PathVariable Long userId) {
    try {
      Post post = postService.savedPost(postId, userId);
      return new ResponseEntity<>(post, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{postId}/user/{userId}/like")
  public ResponseEntity<Post> likePost(@PathVariable Long postId, @PathVariable Long userId) {
    try {
      Post post = postService.likePost(postId, userId);
      return new ResponseEntity<>(post, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
