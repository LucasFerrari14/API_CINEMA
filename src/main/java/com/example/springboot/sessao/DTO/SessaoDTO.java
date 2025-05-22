package com.example.springboot.sessao.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.UUID;

public record SessaoDTO(@NotNull Integer nuSessao, @NotBlank LocalTime hrInicio,
                        @NotBlank LocalTime hrFim, @NotNull UUID cdFilme) {
}
