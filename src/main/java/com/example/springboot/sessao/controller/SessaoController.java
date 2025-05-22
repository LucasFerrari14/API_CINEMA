package com.example.springboot.sessao.controller;


import com.example.springboot.sessao.DTO.SessaoDTO;
import com.example.springboot.sessao.model.SessaoModel;
import com.example.springboot.sessao.repositorio.SessaoRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SessaoController {
    @Autowired
    SessaoRepositorio sessaoRepositorio;

    @PostMapping("/sessao")
    public ResponseEntity<SessaoModel> saveSessao(@RequestBody @Valid SessaoDTO sessaoDTO) {
        SessaoModel sessaoModel = new SessaoModel();
        BeanUtils.copyProperties(sessaoDTO, sessaoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoRepositorio.save(sessaoModel));
    }
    @GetMapping("/sessao")
    public ResponseEntity<List<SessaoModel>> getAllSessao() {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoRepositorio.findAll());
    }
    @GetMapping("/sessao/{cdSessao}")
    public ResponseEntity<Object> getOneSessao(@PathVariable(value="cdSessao") UUID id) {
        Optional<SessaoModel> sessao0 = sessaoRepositorio.findById(id);
        if(sessao0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sessao não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sessao0.get());
    }
    @PutMapping("/sessao/{cdSessao}")
    public ResponseEntity<Object> updateSessao(@PathVariable(value="cdSessao") UUID cdSessao,
                                                @RequestBody @Valid SessaoDTO sessaoDTO) {
        Optional<SessaoModel> sessao0 = sessaoRepositorio.findById(cdSessao);
        if(sessao0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sessao não encontrada");
        }
        var sessaoModel = sessao0.get();
        BeanUtils.copyProperties(sessaoDTO, sessaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(sessaoRepositorio.save(sessaoModel));
    }

    @DeleteMapping("/Sessao/{cdSessao}")
    public ResponseEntity<Object> deleteSessao(@PathVariable(value="cdSessao") UUID cdSessao) {
        Optional<SessaoModel> sessao0 = sessaoRepositorio.findById(cdSessao);
        if(sessao0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sessao não encontrada");
        }
        sessaoRepositorio.delete(sessao0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Sessao deletada com sucesso");
    }
}
