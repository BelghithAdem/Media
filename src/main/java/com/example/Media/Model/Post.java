package com.example.Media.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String caption;
  private String image;
  private String video;

  @JsonIgnore
  @ManyToOne
  private Utilisateur user;

  @OneToMany
  private List<Utilisateur> liked = new ArrayList<>();

  private LocalDateTime createdAt;

  // Constructors
  public Post() {
  }

  public Post(String caption, String image, String video, Utilisateur user, LocalDateTime createdAt) {
    this.caption = caption;
    this.image = image;
    this.video = video;
    this.user = user;
    this.createdAt = createdAt;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public Utilisateur getUser() {
    return user;
  }

  public void setUser(Utilisateur user) {
    this.user = user;
  }

  public List<Utilisateur> getLiked() {
    return liked;
  }

  public void setLiked(List<Utilisateur> liked) {
    this.liked = liked;
  }



  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
