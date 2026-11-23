package com.chaussures.backend.dto;

import com.chaussures.backend.entities.Chaussure;
import com.chaussures.backend.entities.ModelDeChaussure;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoMapper {

    public ChaussureDto toDto(Chaussure chaussure) {
        if (chaussure == null) {
            return null;
        }
        return new ChaussureDto(
                chaussure.getId(),
                chaussure.getNom(),
                chaussure.getPrix(),
                chaussure.getTaille(),
                toDto(chaussure.getModelDeChaussure()));
    }

    public List<ChaussureDto> toChaussureDtos(List<Chaussure> chaussures) {
        return chaussures.stream().map(this::toDto).toList();
    }

    public Chaussure toEntity(ChaussureDto dto) {
        if (dto == null) {
            return null;
        }
        return new Chaussure(dto.getNom(), dto.getPrix(), dto.getTaille(), toEntity(dto.getModelDeChaussure()));
    }

    public ModelDeChaussureDto toDto(ModelDeChaussure modele) {
        if (modele == null) {
            return null;
        }
        return new ModelDeChaussureDto(modele.getIdModel(), modele.getNomModel());
    }

    public List<ModelDeChaussureDto> toModeleDtos(List<ModelDeChaussure> modeles) {
        return modeles.stream().map(this::toDto).toList();
    }

    public ModelDeChaussure toEntity(ModelDeChaussureDto dto) {
        if (dto == null) {
            return null;
        }
        ModelDeChaussure modele = new ModelDeChaussure(dto.getNomModel());
        modele.setIdModel(dto.getIdModel());
        return modele;
    }
}
