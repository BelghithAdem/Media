package com.example.Media.Repository;

import com.example.Media.Model.MsgTexte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgTexteRepository extends JpaRepository<MsgTexte, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
