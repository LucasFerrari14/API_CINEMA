package com.example.springboot.assento.service;


import com.example.springboot.assento.DTO.AssentoDTO;
import com.example.springboot.assento.model.AssentoModel;
import com.example.springboot.assento.repositorio.AssentoRepositorio;
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
public class AssentoService {
    @Autowired
    private AssentoRepositorio assentoRepository;

    public Optional<AssentoModel> findById(UUID id) {
        return assentoRepository.findById(id);
    }
    public List<AssentoModel> listAll() {
        return assentoRepository.findAll();
    }

    public AssentoModel save(@RequestBody @Valid AssentoDTO AssentoDTO) {
        AssentoModel assento = new AssentoModel();
        BeanUtils.copyProperties(AssentoDTO, assento);
        assentoRepository.save(assento);
        return assento;
    }

    public AssentoModel update(@NotNull AssentoModel assento) {
        return assentoRepository.save(assento);
    }

    public void delete(@NotNull AssentoModel assento) {
        assentoRepository.delete(assento);
    }
}
