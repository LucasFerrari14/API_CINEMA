package com.example.springboot.pessoa.controller;


import com.example.springboot.pessoa.service.PessoaService;
import com.example.springboot.pessoa.DTO.PessoaDTO;
import com.example.springboot.pessoa.model.PessoaModel;
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
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/pessoas")
    public ResponseEntity<PessoaModel> savePessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaDTO));
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<PessoaModel>> getAllPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listAll());
    }

    @GetMapping("/pessoas/{cdPessoa}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value="cdPessoa") UUID id) {
        Optional<PessoaModel> pessoa = pessoaService.findById(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa.get());
    }

    @PutMapping("/pessoas/{cdPessoa}")
    public ResponseEntity<Object> updatePessoa(@PathVariable(value="cdPessoa") UUID cdPessoa,
                                               @RequestBody @Valid PessoaDTO pessoaDTO) {
        Optional<PessoaModel> pessoa = pessoaService.findById(cdPessoa);
        if(pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        var pessoaModel = pessoa.get();
        BeanUtils.copyProperties(pessoaDTO, pessoaModel);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.update(pessoaModel));
    }

    @DeleteMapping("/pessoas/{cdPessoa}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value="cdPessoa") UUID cdPessoa) {
        Optional<PessoaModel> pessoa = pessoaService.findById(cdPessoa);
        if(pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        pessoaService.delete(pessoa.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso");
    }
}
