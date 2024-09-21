package com.example.Media.Controller;

import com.example.Media.Model.Comment;
import com.example.Media.Model.Post;

import com.example.Media.Model.Utilisateur;
import com.example.Media.Services.CommentService;
import com.example.Media.Services.PostService;
<<<<<<< HEAD
import com.example.Media.Services.UtilisateurService;
=======
>>>>>>> master
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
<<<<<<< HEAD
=======
import java.util.Collections;
>>>>>>> master
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
<<<<<<< HEAD
    @RequestParam("caption") String caption,
    @RequestParam("mediaFile") MultipartFile mediaFile,
    @PathVariable Long userId) {
=======
      @RequestParam("caption") String caption,
      @RequestParam("mediaFile") MultipartFile mediaFile,
      @PathVariable Long userId) {
>>>>>>> master

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
<<<<<<< HEAD
      // Utilisez le service pour récupérer les données de l'image par le nom de fichier
=======
      // Utilisez le service pour récupérer les données de l'image par le nom de
      // fichier
>>>>>>> master
      byte[] imageData = postService.getImageDataByName(fileName);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_JPEG);

      return new ResponseEntity<>(new ByteArrayResource(imageData), headers, HttpStatus.OK);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

<<<<<<< HEAD


=======
>>>>>>> master
  @GetMapping("/all")
  public ResponseEntity<List<Post>> getAllPosts() {
    try {
      List<Post> posts = postService.findAllPosts();
<<<<<<< HEAD
=======
      // Sort the posts in descending order based on their timestamps
      Collections.sort(posts, (post1, post2) -> post2.getDateCreated().compareTo(post1.getDateCreated()));
>>>>>>> master
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

<<<<<<< HEAD



=======
>>>>>>> master
  @PutMapping("/{postId}/user/{userId}/save")
  public ResponseEntity<Post> savePost(@PathVariable Long postId, @PathVariable Long userId) {
    try {
      Post post = postService.savedPost(postId, userId);
      return new ResponseEntity<>(post, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

<<<<<<< HEAD

=======
>>>>>>> master
  @PostMapping("/posts/{postId}/like")
  public ResponseEntity<?> likePost(@PathVariable("postId") Long postId, @RequestParam Long userId) {
    try {
      postService.likePost(postId, userId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
<<<<<<< HEAD
  @GetMapping("/{postId}/likes")
  public ResponseEntity<?> getPostLikes(@PathVariable("postId") Long postId,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size) {
=======

  @GetMapping("/{postId}/likes")
  public ResponseEntity<?> getPostLikes(@PathVariable("postId") Long postId,
      @RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "size", defaultValue = "5") Integer size) {
>>>>>>> master
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
<<<<<<< HEAD
=======

  @GetMapping("/user/{userId}/posts")
  public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
    try {
      List<Post> posts = postService.getPostsByUserId(userId);
      return new ResponseEntity<>(posts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

>>>>>>> master
  @PostMapping("/posts/{postId}/unlike")
  public ResponseEntity<?> unlikePost(@PathVariable("postId") Long postId, @RequestParam Long userId) {
    try {
      postService.unlikePost(postId, userId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
<<<<<<< HEAD
  @GetMapping("/posts/{postId}/comments")
  public ResponseEntity<?> getPostComments(@PathVariable("postId") Long postId,
                                           @RequestParam("page") Integer page,
                                           @RequestParam("size") Integer size,
                                           @RequestParam("userId") Long userId) {
    page = page < 0 ? 0 : page-1;
    size = size <= 0 ? 5 : size;
    Post targetPost = postService.findPostById(postId);
    List<CommentResponse> postCommentResponseList = commentService.getPostCommentsPaginate(targetPost, page, size, userId);
=======

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
>>>>>>> master
    return new ResponseEntity<>(postCommentResponseList, HttpStatus.OK);
  }

  @PostMapping("/{postId}/comments/create")
  public ResponseEntity<?> createPostComment(@PathVariable("postId") Long postId,
<<<<<<< HEAD
                                             @RequestParam(value = "content") String content,
                                             @RequestParam(value = "userId") Long userId) {
    Comment savedComment = postService.createPostComment(postId, content, userId);
    CommentResponse commentResponse = CommentResponse.builder()
      .comment(savedComment)
      .likedByAuthUser(false)
      .build();
=======
      @RequestParam(value = "content") String content,
      @RequestParam(value = "userId") Long userId) {
    Comment savedComment = postService.createPostComment(postId, content, userId);
    CommentResponse commentResponse = CommentResponse.builder()
        .comment(savedComment)
        .likedByAuthUser(false)
        .build();
>>>>>>> master
    return new ResponseEntity<>(commentResponse, HttpStatus.OK);
  }

  @PostMapping("/posts/{postId}/comments/{commentId}/update")
  public ResponseEntity<?> updatePostComment(@PathVariable("commentId") Long commentId,
<<<<<<< HEAD
                                             @PathVariable("postId") Long postId,
                                             @RequestParam(value = "content") String content,
                                             @RequestParam(value = "userId") Long userId) {
=======
      @PathVariable("postId") Long postId,
      @RequestParam(value = "content") String content,
      @RequestParam(value = "userId") Long userId) {
>>>>>>> master
    Comment savedComment = postService.updatePostComment(commentId, postId, content, userId);
    return new ResponseEntity<>(savedComment, HttpStatus.OK);
  }

  @PostMapping("/posts/{postId}/comments/{commentId}/delete")
  public ResponseEntity<?> deletePostComment(@PathVariable("commentId") Long commentId,
<<<<<<< HEAD
                                             @PathVariable("postId") Long postId,
                                             @RequestParam(value = "userId") Long userId) {
=======
      @PathVariable("postId") Long postId,
      @RequestParam(value = "userId") Long userId) {
>>>>>>> master
    postService.deletePostComment(commentId, postId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/posts/comments/{commentId}/like")
  public ResponseEntity<?> likePostComment(@PathVariable("commentId") Long commentId,
<<<<<<< HEAD
                                           @RequestParam(value = "userId") Long userId) {
=======
      @RequestParam(value = "userId") Long userId) {
>>>>>>> master
    commentService.likeComment(commentId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/posts/comments/{commentId}/unlike")
  public ResponseEntity<?> unlikePostComment(@PathVariable("commentId") Long commentId,
<<<<<<< HEAD
                                             @RequestParam(value = "userId") Long userId) {
=======
      @RequestParam(value = "userId") Long userId) {
>>>>>>> master
    commentService.unlikeComment(commentId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/posts/comments/{commentId}/likes")
  public ResponseEntity<?> getCommentLikeList(@PathVariable("commentId") Long commentId,
<<<<<<< HEAD
                                              @RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {
    page = page < 0 ? 0 : page-1;
=======
      @RequestParam("page") Integer page,
      @RequestParam("size") Integer size) {
    page = page < 0 ? 0 : page - 1;
>>>>>>> master
    size = size <= 0 ? 5 : size;
    Comment targetComment = commentService.getCommentById(commentId);
    List<Utilisateur> commentLikes = commentService.getLikesByCommentPaginate(targetComment, page, size);
    return new ResponseEntity<>(commentLikes, HttpStatus.OK);
  }

}
