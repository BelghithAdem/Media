package com.example.Media.Repository;

import com.example.Media.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
