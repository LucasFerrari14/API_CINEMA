package com.example.springboot.assento.controller;


import com.example.springboot.assento.DTO.AssentoDTO;
import com.example.springboot.assento.model.AssentoModel;
import com.example.springboot.assento.repositorio.AssentoRepositorio;
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
    AssentoRepositorio assentoRepositorio;

    @PostMapping("/assento")
    public ResponseEntity<AssentoModel> saveAssento(@RequestBody @Valid AssentoDTO assentoDTO) {
        AssentoModel assentoModel = new AssentoModel();
        BeanUtils.copyProperties(assentoDTO, assentoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(assentoRepositorio.save(assentoModel));
    }
    @GetMapping("/assento")
    public ResponseEntity<List<AssentoModel>> getAllAssento() {
        return ResponseEntity.status(HttpStatus.OK).body(assentoRepositorio.findAll());
    }
    @GetMapping("/assento/{cdAssento}")
    public ResponseEntity<Object> getOneAssento(@PathVariable(value="cdAssento") UUID id) {
        Optional<AssentoModel> assento0 = assentoRepositorio.findById(id);
        if(assento0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(assento0.get());
    }
    @PutMapping("/assento/{cdAssento}")
    public ResponseEntity<Object> updateAssento(@PathVariable(value="cdAssento") UUID cdAssento,
                                              @RequestBody @Valid AssentoDTO assentoDTO) {
        Optional<AssentoModel> assento0 = assentoRepositorio.findById(cdAssento);
        if(assento0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assento não encontrado");
        }
        var assentoModel = assento0.get();
        BeanUtils.copyProperties(assentoDTO, assentoModel);
        return ResponseEntity.status(HttpStatus.OK).body(assentoRepositorio.save(assentoModel));
    }

    @DeleteMapping("/assento/{cdAssento}")
    public ResponseEntity<Object> deleteAssento(@PathVariable(value="cdAssento") UUID cdAssento) {
        Optional<AssentoModel> assento0 = assentoRepositorio.findById(cdAssento);
        if(assento0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assento não encontrado");
        }
        assentoRepositorio.delete(assento0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Assento deletado com sucesso");
    }
}
