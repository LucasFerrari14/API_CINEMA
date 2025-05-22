package com.example.springboot.filme.controller;

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

    @PostMapping("/pessoas")
    public ResponseEntity<FilmeModel> savePessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {
        FilmeModel pessoaModel = new FilmeModel();
        BeanUtils.copyProperties(pessoaDTO, pessoaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepositorio.save(pessoaModel));
    }
    @GetMapping("/pessoas")
    public ResponseEntity<List<FilmeModel>> getAllPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepositorio.findAll());
    }
    @GetMapping("/pessoas/{cdPessoa}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value="cdPessoa") UUID id) {
        Optional<FilmeModel> pessoa0 = pessoaRepositorio.findById(id);
        if(pessoa0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa0.get());
    }
    @PutMapping("/pessoas/{cdPessoa}")
    public ResponseEntity<Object> updatePessoa(@PathVariable(value="cdPessoa") UUID cdPessoa,
                                               @RequestBody @Valid PessoaDTO pessoaDTO) {
        Optional<FilmeModel\> pessoa0 = pessoaRepositorio.findById(cdPessoa);
        if(pessoa0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        var pessoaModel = pessoa0.get();
        BeanUtils.copyProperties(pessoaDTO, pessoaModel);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepositorio.save(pessoaModel));
    }

    @DeleteMapping("/products/{cdPessoa}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value="cdPessoa") UUID cdPessoa) {
        Optional<PessoaModel> pessoa0 = pessoaRepositorio.findById(cdPessoa);
        if(pessoa0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        pessoaRepositorio.delete(pessoa0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso");
    }

}
