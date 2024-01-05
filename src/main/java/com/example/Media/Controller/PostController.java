package com.example.Media.Controller;

import com.example.Media.Model.Post;

import com.example.Media.Services.PostService;
import com.example.Media.advice.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller

@RestController
@RequestMapping("/api/posts")
public class PostController {

  @Autowired
  private PostService postService;

  @PostMapping("/user/{userId}")
  public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Long userId) throws Exception {
    Post createdPost = postService.createNewPost(post, userId);
    return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
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
  public ResponseEntity<Post> findPostById(@PathVariable Long postId) throws Exception {
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
