package com.chaussures.backend.service;

import com.chaussures.backend.entities.ModelDeChaussure;
import com.chaussures.backend.exception.ConflictException;
import com.chaussures.backend.exception.ResourceNotFoundException;
import com.chaussures.backend.repos.ChaussureRepository;
import com.chaussures.backend.repos.ModelDeChaussureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModelDeChaussureServiceImpl implements ModelDeChaussureService {

    private final ModelDeChaussureRepository modelRepository;
    private final ChaussureRepository chaussureRepository;

    public ModelDeChaussureServiceImpl(ModelDeChaussureRepository modelRepository,
                                       ChaussureRepository chaussureRepository) {
        this.modelRepository = modelRepository;
        this.chaussureRepository = chaussureRepository;
    }

    @Override
    public List<ModelDeChaussure> getAllModeles() {
        return modelRepository.findAll();
    }

    @Override
    public ModelDeChaussure getModele(Long idModel) {
        return modelRepository.findById(idModel)
                .orElseThrow(() -> new ResourceNotFoundException("Modèle introuvable : " + idModel));
    }

    @Override
    public ModelDeChaussure saveModele(ModelDeChaussure modele) {
        String nom = normalize(modele.getNomModel());
        if (modelRepository.findByNomModelIgnoreCase(nom).isPresent()) {
            throw new ConflictException("Ce modèle existe déjà.");
        }
        modele.setIdModel(null);
        modele.setNomModel(nom);
        return modelRepository.save(modele);
    }

    @Override
    public ModelDeChaussure updateModele(Long idModel, ModelDeChaussure modele) {
        ModelDeChaussure existant = getModele(idModel);
        String nom = normalize(modele.getNomModel());

        modelRepository.findByNomModelIgnoreCase(nom)
                .filter(other -> !other.getIdModel().equals(idModel))
                .ifPresent(other -> {
                    throw new ConflictException("Ce modèle existe déjà.");
                });

        existant.setNomModel(nom);
        return modelRepository.save(existant);
    }

    @Override
    public void deleteModele(Long idModel) {
        ModelDeChaussure modele = getModele(idModel);
        if (!chaussureRepository.findByModelDeChaussureIdModel(idModel).isEmpty()) {
            throw new ConflictException("Impossible de supprimer : ce modèle est utilisé par des chaussures.");
        }
        modelRepository.delete(modele);
    }

    private String normalize(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new ConflictException("Le nom du modèle est obligatoire.");
        }
        return nom.trim();
    }
}
