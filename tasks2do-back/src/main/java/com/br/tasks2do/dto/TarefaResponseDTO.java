package com.br.tasks2do.dto;

import com.br.tasks2do.model.tarefas.StatusDaTarefa;
import com.br.tasks2do.model.tarefas.Tarefas;

import java.time.LocalDate;

public record TarefaResponseDTO(Integer id,
                                String nome,
                                String descricao,
                                LocalDate data_de_adicao,
                                StatusDaTarefa statusDaTarefa,
                                Integer usuarioId) {
    public TarefaResponseDTO(Tarefas novaTarefa, int usuarioId) {
        this(novaTarefa.getTarefas_id(), novaTarefa.getNome(),
                novaTarefa.getDescricao(), novaTarefa.getData_de_adicao(),
                novaTarefa.getStatus_da_tarefa(), usuarioId );
    }
}
