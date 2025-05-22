package com.example.springboot.filme.controller;

import com.example.springboot.filme.DTO.FilmeDTO;
import com.example.springboot.filme.model.FilmeModel;
import com.example.springboot.filme.repositorio.FilmeRepositorio;

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
    FilmeRepositorio filmeRepositorio;

    @PostMapping("/filmes")
    public ResponseEntity<FilmeModel> savefilme(@RequestBody @Valid FilmeDTO filmeDTO) {
        FilmeModel filmeModel = new FilmeModel();
        BeanUtils.copyProperties(filmeDTO, filmeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeRepositorio.save(filmeModel));
    }

    @GetMapping("/filmes")
    public ResponseEntity<List<FilmeModel>> getAllFilmes() {
        return ResponseEntity.status(HttpStatus.OK).body(filmeRepositorio.findAll());
    }

    @GetMapping("/filmes/{cdFilme}")
    public ResponseEntity<Object> getOneFilme(@PathVariable(value="cdFilme") UUID id) {
        Optional<FilmeModel> filme0 = filmeRepositorio.findById(id);
        if(filme0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filme0.get());
    }

    @PutMapping("/filmes/{cdFilme}")
    public ResponseEntity<Object> updatefilme(@PathVariable(value="cdFilme") UUID cdFilme,
                                               @RequestBody @Valid FilmeDTO filmeDTO) {
        Optional<FilmeModel> filme0 = filmeRepositorio.findById(cdFilme);
        if(filme0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        }
        var filmeModel = filme0.get();
        BeanUtils.copyProperties(filmeDTO, filmeModel);
        return ResponseEntity.status(HttpStatus.OK).body(filmeRepositorio.save(filmeModel));
    }

    @DeleteMapping("/filmes/{cdFilme}")
    public ResponseEntity<Object> deletefilme(@PathVariable(value="cdFilme") UUID cdFilme) {
        Optional<FilmeModel> filme0 = filmeRepositorio.findById(cdFilme);
        if(filme0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        }
        filmeRepositorio.delete(filme0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Filme deletado com sucesso");
    }
}
