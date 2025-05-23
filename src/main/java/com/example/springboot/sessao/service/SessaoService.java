package com.example.springboot.sessao.service;

import com.example.springboot.sessao.DTO.SessaoDTO;
import com.example.springboot.sessao.model.SessaoModel;
import com.example.springboot.sessao.repositorio.SessaoRepositorio;
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
public class SessaoService {
    
    @Autowired
    private SessaoRepositorio sessaoRepositorio;

    public Optional<SessaoModel> findById(UUID id) {
        return sessaoRepositorio.findById(id);
    }
    public List<SessaoModel> listAll() {
        return sessaoRepositorio.findAll();
    }

    public SessaoModel save(@RequestBody @Valid SessaoDTO sessaoDTO) {
        SessaoModel service = new SessaoModel();
        BeanUtils.copyProperties(sessaoDTO, service);
        sessaoRepositorio.save(service);
        return service;
    }

    public SessaoModel update(@NotNull SessaoModel service) {
        return sessaoRepositorio.save(service);
    }

    public void delete(@NotNull SessaoModel service) {
        sessaoRepositorio.delete(service);
    }
}
