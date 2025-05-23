package com.example.springboot.filme.controller;


import com.example.springboot.filme.DTO.FilmeDTO;
import com.example.springboot.filme.model.FilmeModel;
import com.example.springboot.filme.service.FilmeService;
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
public class FilmeController {
    @Autowired
    FilmeService filmeService;

    @PostMapping("/filmes")
    public ResponseEntity<FilmeModel> saveFilme(@RequestBody @Valid FilmeDTO filmeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.save(filmeDTO));
    }

    @GetMapping("/filmes")
    public ResponseEntity<List<FilmeModel>> getAllFilmes() {
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.listAll());
    }

    @GetMapping("/filmes/{cdfilme}")
    public ResponseEntity<Object> getOneFilme(@PathVariable(value="cdfilme") UUID id) {
        Optional<FilmeModel> filme = filmeService.findById(id);
        if (filme.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("filme não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filme.get());
    }

    @PutMapping("/filmes/{cdfilme}")
    public ResponseEntity<Object> updateFilme(@PathVariable(value="cdfilme") UUID cdfilme,
                                               @RequestBody @Valid FilmeDTO filmeDTO) {
        Optional<FilmeModel> filme = filmeService.findById(cdfilme);
        if(filme.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("filme não encontrada");
        }
        var filmeModel = filme.get();
        BeanUtils.copyProperties(filmeDTO, filmeModel);
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.update(filmeModel));
    }

    @DeleteMapping("/filmes/{cdfilme}")
    public ResponseEntity<Object> deleteFilme(@PathVariable(value="cdfilme") UUID cdfilme) {
        Optional<FilmeModel> filme = filmeService.findById(cdfilme);
        if(filme.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("filme não encontrada");
        }
        filmeService.delete(filme.get());
        return ResponseEntity.status(HttpStatus.OK).body("filme deletada com sucesso");
    }
}
