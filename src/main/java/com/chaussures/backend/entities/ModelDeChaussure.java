package com.chaussures.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "model_de_chaussure")
public class ModelDeChaussure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model")
    private Long idModel;

    @NotBlank
    @Column(name = "nom_model", nullable = false, unique = true)
    private String nomModel;

    @JsonIgnore
    @OneToMany(mappedBy = "modelDeChaussure", cascade = CascadeType.REMOVE)
    private List<Chaussure> chaussures = new ArrayList<>();

    public ModelDeChaussure() {
    }

    public ModelDeChaussure(String nomModel) {
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

    public List<Chaussure> getChaussures() {
        return chaussures;
    }

    public void setChaussures(List<Chaussure> chaussures) {
        this.chaussures = chaussures;
    }
}
