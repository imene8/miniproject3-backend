package com.chaussures.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ChaussureDto {

    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private Double prix;

    @NotNull(message = "La taille est obligatoire")
    @Positive(message = "La taille doit être positive")
    private Integer taille;

    @NotNull(message = "Le modèle est obligatoire")
    private ModelDeChaussureDto modelDeChaussure;

    public ChaussureDto() {
    }

    public ChaussureDto(Long id, String nom, Double prix, Integer taille, ModelDeChaussureDto modelDeChaussure) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.taille = taille;
        this.modelDeChaussure = modelDeChaussure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    public ModelDeChaussureDto getModelDeChaussure() {
        return modelDeChaussure;
    }

    public void setModelDeChaussure(ModelDeChaussureDto modelDeChaussure) {
        this.modelDeChaussure = modelDeChaussure;
    }
}
