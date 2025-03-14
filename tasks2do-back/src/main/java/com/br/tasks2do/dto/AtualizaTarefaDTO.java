package com.br.tasks2do.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizaTarefaDTO(@NotNull
                                Integer id,

                                @NotBlank
                                String nome,

                                String descricao) {
}
