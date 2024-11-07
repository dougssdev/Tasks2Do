package com.br.tasks2do.model.tarefas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CadastraTarefa(
        @NotBlank
        String nome,

        String descricao,

        @NotNull
        Date    data_de_adicao,

        @NotNull
        Integer usuario
) {
}
