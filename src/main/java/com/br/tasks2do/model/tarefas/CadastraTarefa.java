package com.br.tasks2do.model.tarefas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

public record CadastraTarefa(
        @NotBlank
        String nome,

        String descricao

) {
}
