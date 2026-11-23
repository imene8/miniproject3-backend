package com.chaussures.backend.repos;

import com.chaussures.backend.entities.Chaussure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChaussureRepository extends JpaRepository<Chaussure, Long> {

    List<Chaussure> findByModelDeChaussureIdModel(Long idModel);

    List<Chaussure> findByNomContainingIgnoreCase(String nom);
}
