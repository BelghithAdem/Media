package com.example.Media.Repository;

import com.example.Media.Model.DemandeAjout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeAjoutRepository extends JpaRepository<DemandeAjout, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
