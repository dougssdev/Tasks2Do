package com.br.tasks2do.model.tarefas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastraTarefa(
        @NotBlank
        String nome,

        String descricao,

        @NotNull
        StatusDaTarefa status
) {
}
