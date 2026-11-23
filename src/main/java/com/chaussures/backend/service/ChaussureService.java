package com.chaussures.backend.service;

import com.chaussures.backend.entities.Chaussure;

import java.util.List;

public interface ChaussureService {

    List<Chaussure> getAllChaussures();

    Chaussure getChaussure(Long id);

    Chaussure saveChaussure(Chaussure chaussure);

    Chaussure updateChaussure(Long id, Chaussure chaussure);

    void deleteChaussure(Long id);

    List<Chaussure> findByModele(Long idModel);

    List<Chaussure> findByNom(String nom);
}
