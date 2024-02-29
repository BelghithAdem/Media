package com.example.Media.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String caption;

  private String imageFileName;
  private String videoFileName; // Nouveau champ pour stocker le nom du fichier de l'image

  private Integer likeCount;
  private Integer commentCount;
  private Integer shareCount;
  @ManyToOne
  private Utilisateur user;


  @Column(nullable = false)
  private Boolean isTypeShare;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date dateCreated;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date dateLastModified;
  @JsonIgnore
  @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
  private List<Comment> postComments = new ArrayList<>();

  @JsonIgnore
  @ManyToMany
  @JoinTable(
    name = "post_likes",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "liker_id")
  )
  private List<Utilisateur> likeList = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "shared_post_id")
  private Post sharedPost;

  @JsonIgnore
  @OneToMany(mappedBy = "sharedPost")
  private List<Post> shareList = new ArrayList<>();





  // Constructors

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Post post = (Post) o;
    return Objects.equals(id, post.id) && Objects.equals(user, post.user);
  }


  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getImageFileName() {
    return imageFileName;
  }

  public void setVideoFileName(String imageFileName) {
    this.videoFileName = videoFileName;
  }
  public String getVideoFileName() {
    return videoFileName;
  }

  public void setImageFileName(String imageFileName) {
    this.imageFileName = imageFileName;
  }

  public Utilisateur getUser() {
    return user;
  }

  public void setUser(Utilisateur user) {
    this.user = user;
  }




}
