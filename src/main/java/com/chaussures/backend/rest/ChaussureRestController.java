package com.chaussures.backend.rest;

import com.chaussures.backend.dto.ChaussureDto;
import com.chaussures.backend.dto.DtoMapper;
import com.chaussures.backend.service.ChaussureService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chaussures")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
public class ChaussureRestController {

    private final ChaussureService chaussureService;
    private final DtoMapper mapper;

    public ChaussureRestController(ChaussureService chaussureService, DtoMapper mapper) {
        this.chaussureService = chaussureService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ChaussureDto> getAllChaussures() {
        return mapper.toChaussureDtos(chaussureService.getAllChaussures());
    }

    @GetMapping("/{id}")
    public ChaussureDto getChaussure(@PathVariable Long id) {
        return mapper.toDto(chaussureService.getChaussure(id));
    }

    @GetMapping("/model/{idModel}")
    public List<ChaussureDto> getChaussuresByModele(@PathVariable Long idModel) {
        return mapper.toChaussureDtos(chaussureService.findByModele(idModel));
    }

    @GetMapping("/recherche")
    public List<ChaussureDto> rechercherParNom(@RequestParam String nom) {
        return mapper.toChaussureDtos(chaussureService.findByNom(nom));
    }

    @PostMapping
    public ResponseEntity<ChaussureDto> createChaussure(@Valid @RequestBody ChaussureDto chaussure) {
        return ResponseEntity.ok(mapper.toDto(chaussureService.saveChaussure(mapper.toEntity(chaussure))));
    }

    @PutMapping("/{id}")
    public ChaussureDto updateChaussure(@PathVariable Long id, @Valid @RequestBody ChaussureDto chaussure) {
        return mapper.toDto(chaussureService.updateChaussure(id, mapper.toEntity(chaussure)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChaussure(@PathVariable Long id) {
        chaussureService.deleteChaussure(id);
        return ResponseEntity.noContent().build();
    }
}
