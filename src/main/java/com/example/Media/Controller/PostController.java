package com.example.Media.Controller;

import com.example.Media.Model.Comment;
import com.example.Media.Model.Post;

import com.example.Media.Model.Utilisateur;
import com.example.Media.Services.CommentService;
import com.example.Media.Services.PostService;
import com.example.Media.advice.ApiResponse;
import com.example.Media.advice.CommentResponse;
import lombok.RequiredArgsConstructor;
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
import java.util.Collections;
import java.util.List;

// Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/posts")
public class PostController {

  @Autowired
  private PostService postService;
  @Autowired
  private CommentService commentService;

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
      // Utilisez le service pour récupérer les données de l'image par le nom de
      // fichier
      byte[] imageData = postService.getImageDataByName(fileName);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_JPEG);

      return new ResponseEntity<>(new ByteArrayResource(imageData), headers, HttpStatus.OK);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<Post>> getAllPosts() {
    try {
      List<Post> posts = postService.findAllPosts();
      // Sort the posts in descending order based on their timestamps
      Collections.sort(posts, (post1, post2) -> post2.getDateCreated().compareTo(post1.getDateCreated()));
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

  @PutMapping("/{postId}/user/{userId}/save")
  public ResponseEntity<Post> savePost(@PathVariable Long postId, @PathVariable Long userId) {
    try {
      Post post = postService.savedPost(postId, userId);
      return new ResponseEntity<>(post, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/posts/{postId}/like")
  public ResponseEntity<?> likePost(@PathVariable("postId") Long postId, @RequestParam Long userId) {
    try {
      postService.likePost(postId, userId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @GetMapping("/{postId}/likes")
  public ResponseEntity<?> getPostLikes(@PathVariable("postId") Long postId,
      @RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "size", defaultValue = "5") Integer size) {
    try {
      page = page < 0 ? 0 : page - 1;
      size = size <= 0 ? 5 : size;
      Post targetPost = postService.findPostById(postId);
      List<Utilisateur> postLikerList = postService.getLikesByPostPaginate(targetPost, page, size);
      return new ResponseEntity<>(postLikerList, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @GetMapping("/user/{userId}/posts")
  public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
    try {
      List<Post> posts = postService.getPostsByUserId(userId);
      return new ResponseEntity<>(posts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/posts/{postId}/unlike")
  public ResponseEntity<?> unlikePost(@PathVariable("postId") Long postId, @RequestParam Long userId) {
    try {
      postService.unlikePost(postId, userId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @GetMapping("/posts/{postId}/comments")
  public ResponseEntity<?> getPostComments(@PathVariable("postId") Long postId,
      @RequestParam("page") Integer page,
      @RequestParam("size") Integer size,
      @RequestParam("userId") Long userId) {
    page = page < 0 ? 0 : page - 1;
    size = size <= 0 ? 5 : size;
    Post targetPost = postService.findPostById(postId);
    List<CommentResponse> postCommentResponseList = commentService.getPostCommentsPaginate(targetPost, page, size,
        userId);
    return new ResponseEntity<>(postCommentResponseList, HttpStatus.OK);
  }

  @PostMapping("/{postId}/comments/create")
  public ResponseEntity<?> createPostComment(@PathVariable("postId") Long postId,
      @RequestParam(value = "content") String content,
      @RequestParam(value = "userId") Long userId) {
    Comment savedComment = postService.createPostComment(postId, content, userId);
    CommentResponse commentResponse = CommentResponse.builder()
        .comment(savedComment)
        .likedByAuthUser(false)
        .build();
    return new ResponseEntity<>(commentResponse, HttpStatus.OK);
  }

  @PostMapping("/posts/{postId}/comments/{commentId}/update")
  public ResponseEntity<?> updatePostComment(@PathVariable("commentId") Long commentId,
      @PathVariable("postId") Long postId,
      @RequestParam(value = "content") String content,
      @RequestParam(value = "userId") Long userId) {
    Comment savedComment = postService.updatePostComment(commentId, postId, content, userId);
    return new ResponseEntity<>(savedComment, HttpStatus.OK);
  }

  @PostMapping("/posts/{postId}/comments/{commentId}/delete")
  public ResponseEntity<?> deletePostComment(@PathVariable("commentId") Long commentId,
      @PathVariable("postId") Long postId,
      @RequestParam(value = "userId") Long userId) {
    postService.deletePostComment(commentId, postId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/posts/comments/{commentId}/like")
  public ResponseEntity<?> likePostComment(@PathVariable("commentId") Long commentId,
      @RequestParam(value = "userId") Long userId) {
    commentService.likeComment(commentId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/posts/comments/{commentId}/unlike")
  public ResponseEntity<?> unlikePostComment(@PathVariable("commentId") Long commentId,
      @RequestParam(value = "userId") Long userId) {
    commentService.unlikeComment(commentId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/posts/comments/{commentId}/likes")
  public ResponseEntity<?> getCommentLikeList(@PathVariable("commentId") Long commentId,
      @RequestParam("page") Integer page,
      @RequestParam("size") Integer size) {
    page = page < 0 ? 0 : page - 1;
    size = size <= 0 ? 5 : size;
    Comment targetComment = commentService.getCommentById(commentId);
    List<Utilisateur> commentLikes = commentService.getLikesByCommentPaginate(targetComment, page, size);
    return new ResponseEntity<>(commentLikes, HttpStatus.OK);
  }

}
