package com.example.springboot.assento.controller;


import com.example.springboot.assento.DTO.AssentoDTO;
import com.example.springboot.assento.model.AssentoModel;
import com.example.springboot.assento.service.AssentoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AssentoController {
    @Autowired
    AssentoService assentoService;

    @PostMapping("/assentos")
    public ResponseEntity<AssentoModel> saveassento(@RequestBody @Valid AssentoDTO assentoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assentoService.save(assentoDTO));
    }

    @GetMapping("/assentos")
    public ResponseEntity<List<AssentoModel>> getAllassentos() {
        return ResponseEntity.status(HttpStatus.OK).body(assentoService.listAll());
    }

    @GetMapping("/assentos/{cdassento}")
    public ResponseEntity<Object> getOneassento(@PathVariable(value="cdassento") UUID id) {
        Optional<AssentoModel> assento = assentoService.findById(id);
        if (assento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("assento não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(assento.get());
    }

    @PutMapping("/assentos/{cdassento}")
    public ResponseEntity<Object> updateassento(@PathVariable(value="cdassento") UUID cdassento,
                                               @RequestBody @Valid AssentoDTO assentoDTO) {
        Optional<AssentoModel> assento = assentoService.findById(cdassento);
        if(assento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("assento não encontrada");
        }
        var assentoModel = assento.get();
        BeanUtils.copyProperties(assentoDTO, assentoModel);
        return ResponseEntity.status(HttpStatus.OK).body(assentoService.update(assentoModel));
    }

    @DeleteMapping("/assentos/{cdassento}")
    public ResponseEntity<Object> deleteassento(@PathVariable(value="cdassento") UUID cdassento) {
        Optional<AssentoModel> assento = assentoService.findById(cdassento);
        if(assento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("assento não encontrada");
        }
        assentoService.delete(assento.get());
        return ResponseEntity.status(HttpStatus.OK).body("assento deletada com sucesso");
    }
}
