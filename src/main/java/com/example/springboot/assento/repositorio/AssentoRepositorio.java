package com.example.springboot.assento.repositorio;

import com.example.springboot.assento.model.AssentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssentoRepositorio extends JpaRepository<AssentoModel, UUID> {
}
