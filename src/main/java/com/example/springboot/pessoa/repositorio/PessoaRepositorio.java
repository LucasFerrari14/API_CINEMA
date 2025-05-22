package com.example.springboot.pessoa.repositorio;

import com.example.springboot.pessoa.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepositorio extends JpaRepository<PessoaModel, UUID> {

}
