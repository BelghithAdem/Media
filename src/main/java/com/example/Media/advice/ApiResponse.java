package com.example.Media.advice; // Remplacez "Media" par le nom correct de votre package

<<<<<<< HEAD

=======
>>>>>>> master
public class ApiResponse {
  private boolean success;
  private String type;
  private String title;
  private int status;
  private String detail;
  private String instance;
  private String error;
<<<<<<< HEAD

  // Constructeur pour une réponse réussie
  public ApiResponse(String message) {
    this.success = true;
    this.status = 200;
    this.detail = message;
=======
  private String qrCodeUri; // Ajout du champ pour le code QR URI

  // Constructeur pour une réponse réussie avec code QR URI
  public ApiResponse(String message, String qrCodeUri) {
    this.success = true;
    this.status = 200;
    this.detail = message;
    this.qrCodeUri = qrCodeUri; // Assignation du code QR URI
  }

  // Constructeur pour une réponse avec un message uniquement
  public ApiResponse(String message) {
    this.success = true; // Par défaut, une réponse avec un message est considérée comme réussie
    this.status = 200; // Par défaut, un statut de succès
    this.detail = message;
>>>>>>> master
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

<<<<<<< HEAD
=======
  // Getters pour les champs de la réponse

>>>>>>> master
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
<<<<<<< HEAD
=======

  public String getQrCodeUri() {
    return qrCodeUri;
  }
>>>>>>> master
}
