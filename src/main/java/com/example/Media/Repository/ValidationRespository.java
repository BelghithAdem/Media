package com.example.Media.Repository;

import com.example.Media.Model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRespository extends JpaRepository<Validation, Long> {

    Optional<Validation> findByCode(String code);
}
