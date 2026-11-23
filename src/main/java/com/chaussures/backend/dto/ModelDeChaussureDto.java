package com.chaussures.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class ModelDeChaussureDto {

    private Long idModel;

    @NotBlank(message = "Le nom du modèle est obligatoire")
    private String nomModel;

    public ModelDeChaussureDto() {
    }

    public ModelDeChaussureDto(Long idModel, String nomModel) {
        this.idModel = idModel;
        this.nomModel = nomModel;
    }

    public Long getIdModel() {
        return idModel;
    }

    public void setIdModel(Long idModel) {
        this.idModel = idModel;
    }

    public String getNomModel() {
        return nomModel;
    }

    public void setNomModel(String nomModel) {
        this.nomModel = nomModel;
    }
}
