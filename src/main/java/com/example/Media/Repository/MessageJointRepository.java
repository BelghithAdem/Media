package com.example.Media.Repository;

import com.example.Media.Model.MessageJoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageJointRepository extends JpaRepository<MessageJoint, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
