package com.chaussures.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "chaussure")
public class Chaussure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nom;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double prix;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer taille;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_model", nullable = false)
    private ModelDeChaussure modelDeChaussure;

    public Chaussure() {
    }

    public Chaussure(String nom, Double prix, Integer taille, ModelDeChaussure modelDeChaussure) {
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

    public ModelDeChaussure getModelDeChaussure() {
        return modelDeChaussure;
    }

    public void setModelDeChaussure(ModelDeChaussure modelDeChaussure) {
        this.modelDeChaussure = modelDeChaussure;
    }
}
