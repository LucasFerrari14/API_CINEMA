package com.example.springboot.filme.repositorio;

import com.example.springboot.filme.model.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmeRepositorio extends JpaRepository<FilmeModel, UUID> {

}
