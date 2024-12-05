package com.br.tasks2do.model.tarefas;

import jakarta.validation.constraints.NotBlank;

public record CadastraTarefa(
        @NotBlank
        String nome,

        String descricao

) {
}
