package com.example.Media.Services;

import com.example.Media.Model.Avis;
import com.example.Media.Model.Utilisateur;
import com.example.Media.Repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisService {
  private final AvisRepository avisRepository;

  @Autowired
  public AvisService(AvisRepository avisRepository) {
    this.avisRepository = avisRepository;
  }

  public void creer(Avis avis) {
    Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    avis.setUtilisateur(utilisateur);
    this.avisRepository.save(avis);
  }

  public List<Avis> liste() {
    return this.avisRepository.findAll();
  }
}
