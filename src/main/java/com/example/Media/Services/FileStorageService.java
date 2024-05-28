package com.example.Media.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

  private final Path fileStorageLocation;



  public FileStorageService() {
    // Définissez le répertoire de stockage des fichiers. Vous pouvez ajuster cela selon vos besoins.
    this.fileStorageLocation = Path.of("src/main/resources/static/uploads").toAbsolutePath().normalize();

    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (IOException ex) {
      throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
    }
  }

  public Path getFileStorageLocation() {
    return fileStorageLocation;
  }

  public void storeFile(MultipartFile file, String fileName) throws Exception {
    try {
      // Copier le fichier dans le répertoire de stockage (écraser le fichier s'il existe déjà)
      Path targetLocation = this.fileStorageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ex) {
      throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
    }
  }

  public void deleteFile(String fileName) throws Exception {
    try {
      // Supprimer le fichier du répertoire de stockage
      Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
      Files.deleteIfExists(filePath);
    } catch (IOException ex) {
      throw new Exception("Could not delete file " + fileName + ". Please try again!", ex);
    }
  }
}
