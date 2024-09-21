package com.example.Media.Repository;

<<<<<<< HEAD

=======
>>>>>>> master
import com.example.Media.Model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

<<<<<<< HEAD
public interface ValidationRespository extends JpaRepository<Validation,Long> {
=======
public interface ValidationRespository extends JpaRepository<Validation, Long> {
>>>>>>> master

    Optional<Validation> findByCode(String code);
}
