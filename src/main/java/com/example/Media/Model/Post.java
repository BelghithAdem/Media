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

  private String imageFileName;
  private String videoFileName; // Nouveau champ pour stocker le nom du fichier de l'image


  @ManyToOne
  private Utilisateur user;

  @OneToMany
  private List<Utilisateur> liked = new ArrayList<>();

  private LocalDateTime createdAt;

  // Constructors
  public Post() {
  }

  public Post(String caption, String imageFileName,String videoFileName, Utilisateur user, LocalDateTime createdAt) {
    this.caption = caption;
    this.imageFileName = imageFileName;
    this.videoFileName=videoFileName;
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
