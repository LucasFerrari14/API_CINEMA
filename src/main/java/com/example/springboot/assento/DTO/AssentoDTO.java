package com.example.springboot.assento.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record AssentoDTO(@NotNull Integer nuAssento, @NotBlank String deFileira,
                         UUID cdDono, @NotNull UUID cdSessao) {
    public UUID getCdDono() {
        return cdDono;
    }

    public UUID getCdSessao() {
        return cdSessao;
    }

    public AssentoDTO(@NotNull Integer nuAssento, @NotBlank String deFileira, UUID cdDono, @NotNull UUID cdSessao) {
        this.nuAssento = nuAssento;
        this.deFileira = deFileira;
        this.cdDono = cdDono;
        this.cdSessao = cdSessao;
    }
}
