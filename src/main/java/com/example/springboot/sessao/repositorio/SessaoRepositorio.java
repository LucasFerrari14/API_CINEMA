package com.example.springboot.sessao.repositorio;

import com.example.springboot.sessao.model.SessaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessaoRepositorio extends JpaRepository<SessaoModel, UUID> {
}
