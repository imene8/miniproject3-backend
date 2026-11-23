package com.chaussures.backend.service;

import com.chaussures.backend.entities.Chaussure;
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
public class ChaussureServiceImpl implements ChaussureService {

    private final ChaussureRepository chaussureRepository;
    private final ModelDeChaussureRepository modelRepository;

    public ChaussureServiceImpl(ChaussureRepository chaussureRepository,
                                ModelDeChaussureRepository modelRepository) {
        this.chaussureRepository = chaussureRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Chaussure> getAllChaussures() {
        return chaussureRepository.findAll();
    }

    @Override
    public Chaussure getChaussure(Long id) {
        return chaussureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chaussure introuvable : " + id));
    }

    @Override
    public Chaussure saveChaussure(Chaussure chaussure) {
        chaussure.setId(null);
        chaussure.setModelDeChaussure(resolveModele(chaussure.getModelDeChaussure()));
        return chaussureRepository.save(chaussure);
    }

    @Override
    public Chaussure updateChaussure(Long id, Chaussure chaussure) {
        Chaussure existante = getChaussure(id);
        existante.setNom(chaussure.getNom());
        existante.setPrix(chaussure.getPrix());
        existante.setTaille(chaussure.getTaille());
        existante.setModelDeChaussure(resolveModele(chaussure.getModelDeChaussure()));
        return chaussureRepository.save(existante);
    }

    @Override
    public void deleteChaussure(Long id) {
        if (!chaussureRepository.existsById(id)) {
            throw new ResourceNotFoundException("Chaussure introuvable : " + id);
        }
        chaussureRepository.deleteById(id);
    }

    @Override
    public List<Chaussure> findByModele(Long idModel) {
        return chaussureRepository.findByModelDeChaussureIdModel(idModel);
    }

    @Override
    public List<Chaussure> findByNom(String nom) {
        return chaussureRepository.findByNomContainingIgnoreCase(nom);
    }

    private ModelDeChaussure resolveModele(ModelDeChaussure modele) {
        if (modele == null || modele.getIdModel() == null) {
            throw new ConflictException("Un modèle de chaussure valide est obligatoire.");
        }
        return modelRepository.findById(modele.getIdModel())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Modèle de chaussure introuvable : " + modele.getIdModel()));
    }
}
