package com.example.springboot.filme.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record FilmeDTO(@NotBlank String deFilme, @NotNull Date dtInicioCartaz, @NotNull Date dtFimCartaz) {
}
