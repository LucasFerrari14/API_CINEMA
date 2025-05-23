package com.example.springboot.sessao.DTO;

import com.example.springboot.filme.model.FilmeModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.UUID;

public record SessaoDTO(@NotNull Integer nuSessao, @NotNull LocalTime hrInicio,
                        @NotNull LocalTime hrFim, @NotNull UUID cdFilme) {
}
