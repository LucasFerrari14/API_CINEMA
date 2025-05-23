package com.example.springboot.filme.service;


import com.example.springboot.assento.model.AssentoModel;
import com.example.springboot.filme.DTO.FilmeDTO;
import com.example.springboot.filme.model.FilmeModel;
import com.example.springboot.filme.repositorio.FilmeRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepositorio filmeRepositorio;

    public FilmeModel findById(UUID id) {
        if (!this.filmeRepositorio.existsById(id)) throw new RuntimeException("filme não existente!");
        Optional<FilmeModel> filme = filmeRepositorio.findById(id);
        return filme.get();
    }
    public List<FilmeModel> listAll() {
        return filmeRepositorio.findAll();
    }

    public FilmeModel save(@RequestBody @Valid FilmeModel filme) {
        filmeRepositorio.save(filme);
        return filme;
    }

    public FilmeModel update(@NotNull FilmeModel filme) {
        return filmeRepositorio.save(filme);
    }

    public void delete(@NotNull FilmeModel filme) {
        if (!this.filmeRepositorio.existsById(filme.getCdFilme())) throw new RuntimeException("filme não existente!");
        this.filmeRepositorio.delete(filme);
    }

}
