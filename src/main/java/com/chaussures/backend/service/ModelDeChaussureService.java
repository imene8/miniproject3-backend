package com.chaussures.backend.service;

import com.chaussures.backend.entities.ModelDeChaussure;

import java.util.List;

public interface ModelDeChaussureService {

    List<ModelDeChaussure> getAllModeles();

    ModelDeChaussure getModele(Long idModel);

    ModelDeChaussure saveModele(ModelDeChaussure modele);

    ModelDeChaussure updateModele(Long idModel, ModelDeChaussure modele);

    void deleteModele(Long idModel);
}
