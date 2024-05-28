package com.example.Media.dto;

public class PostResponseDTO {
  private String caption;
  private byte[] imageData;

  public PostResponseDTO(String caption, byte[] imageData) {
    this.caption = caption;
    this.imageData = imageData;
  }

  // Ajoutez les getters et setters nécessaires
  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public byte[] getImageData() {
    return imageData;
  }

  public void setImageData(byte[] imageData) {
    this.imageData = imageData;
  }
}
