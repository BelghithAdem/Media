package com.example.Media.advice; // Remplacez "Media" par le nom correct de votre package


public class ApiResponse {
  private boolean success;
  private String type;
  private String title;
  private int status;
  private String detail;
  private String instance;
  private String error;

  // Constructeur pour une réponse réussie
  public ApiResponse(String message) {
    this.success = true;
    this.status = 200;
    this.detail = message;
  }

  // Constructeur pour une réponse d'erreur
  public ApiResponse(int status, String title, String detail, String instance, String error) {
    this.success = false;
    this.status = status;
    this.title = title;
    this.detail = detail;
    this.instance = instance;
    this.error = error;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getType() {
    return type;
  }

  public String getTitle() {
    return title;
  }

  public int getStatus() {
    return status;
  }

  public String getDetail() {
    return detail;
  }

  public String getInstance() {
    return instance;
  }

  public String getError() {
    return error;
  }
}
