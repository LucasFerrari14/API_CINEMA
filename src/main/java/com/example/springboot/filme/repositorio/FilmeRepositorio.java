package com.example.springboot.filme.repositorio;

import com.example.springboot.assento.model.AssentoModel;
import com.example.springboot.filme.model.FilmeModel;
import com.example.springboot.pessoa.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilmeRepositorio extends JpaRepository<FilmeModel, UUID> {

}
