package com.chaussures.backend.rest;

import com.chaussures.backend.dto.DtoMapper;
import com.chaussures.backend.dto.ModelDeChaussureDto;
import com.chaussures.backend.service.ModelDeChaussureService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/modeles")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
public class ModelDeChaussureRestController {

    private final ModelDeChaussureService modelService;
    private final DtoMapper mapper;

    public ModelDeChaussureRestController(ModelDeChaussureService modelService, DtoMapper mapper) {
        this.modelService = modelService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ModelDeChaussureDto> getAllModeles() {
        return mapper.toModeleDtos(modelService.getAllModeles());
    }

    @GetMapping("/{idModel}")
    public ModelDeChaussureDto getModele(@PathVariable Long idModel) {
        return mapper.toDto(modelService.getModele(idModel));
    }

    @PostMapping
    public ResponseEntity<ModelDeChaussureDto> createModele(@Valid @RequestBody ModelDeChaussureDto modele) {
        return ResponseEntity.ok(mapper.toDto(modelService.saveModele(mapper.toEntity(modele))));
    }

    @PutMapping("/{idModel}")
    public ModelDeChaussureDto updateModele(@PathVariable Long idModel,
                                            @Valid @RequestBody ModelDeChaussureDto modele) {
        return mapper.toDto(modelService.updateModele(idModel, mapper.toEntity(modele)));
    }

    @DeleteMapping("/{idModel}")
    public ResponseEntity<Void> deleteModele(@PathVariable Long idModel) {
        modelService.deleteModele(idModel);
        return ResponseEntity.noContent().build();
    }
}
