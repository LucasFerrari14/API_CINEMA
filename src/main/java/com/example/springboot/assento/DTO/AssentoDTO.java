package com.example.springboot.assento.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AssentoDTO(@NotNull Integer nuAssento, @NotBlank String deFileira, @NotNull Date dtFimCartaz) {
}
