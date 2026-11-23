package com.chaussures.backend.repos;

import com.chaussures.backend.entities.ModelDeChaussure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelDeChaussureRepository extends JpaRepository<ModelDeChaussure, Long> {

    Optional<ModelDeChaussure> findByNomModelIgnoreCase(String nomModel);
}
