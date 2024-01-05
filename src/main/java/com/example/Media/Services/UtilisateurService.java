package com.example.Media.Services;


import com.example.Media.Model.Role;
import com.example.Media.Model.Utilisateur;
import com.example.Media.Model.Validation;
import com.example.Media.Repository.UtilisateurRespository;
import com.example.Media.TypeDeRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UtilisateurService implements UserDetailsService {
    private UtilisateurRespository utilisateurRespository;
    private ValidationService validationService;
    private BCryptPasswordEncoder passwordEncoder;

    public void inscription(Utilisateur utilisateur) {
        if (!utilisateur.getUsername().contains("@")) {
            throw new RuntimeException("Votre email est invalide");
        }
        if (!utilisateur.getUsername().contains(".")) {
            throw new RuntimeException("Votre email est invalide");
        }

        // Recherche de l'utilisateur par e-mail
        Optional<Utilisateur> utilisateurOptionel = utilisateurRespository.findByEmail(utilisateur.getUsername());

        if (utilisateurOptionel.isPresent()) {
            throw new RuntimeException("Votre email est déjà utilisé");
        }

        String mdpCrypte = passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setMdp(mdpCrypte);

        final Role roleUtilisateur = new Role();
        roleUtilisateur.setLibelle(TypeDeRole.UTILISATEUR);
        if(utilisateur.getRole() != null && utilisateur.getRole().getLibelle().equals(TypeDeRole.ADMINISTRATEUR)){
          roleUtilisateur.setLibelle(TypeDeRole.ADMINISTRATEUR);
          utilisateur.setActif(true);
        }
      utilisateur.setRole(roleUtilisateur);
        utilisateur = utilisateurRespository.save(utilisateur);
      if(roleUtilisateur.getLibelle().equals(TypeDeRole.UTILISATEUR)) {
        validationService.enregister(utilisateur);
      }

    }




    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.Lireenfonctionducode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpiration())) {
            throw new RuntimeException("Votre code a expiré");
        }

        Utilisateur utilisateurActiver = this.utilisateurRespository.findById(validation.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateurActiver.setActif(true);
        this.utilisateurRespository.save(utilisateurActiver);
    }


    @Override
    public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRespository
                .findByEmail(username)
                .orElseThrow(() -> new  UsernameNotFoundException("Aucun utilisateur ne corespond à cet identifiant"));
    }
}
