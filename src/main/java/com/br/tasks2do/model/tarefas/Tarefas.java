package com.br.tasks2do.model.tarefas;

import com.br.tasks2do.dto.AtualizaTarefaDTO;
import com.br.tasks2do.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Entity (name = "Tarefas")
@Table (name = "tarefas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefas {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int tarefas_id;
    private String nome;
    private String descricao;
    private LocalDate data_de_adicao;
    private StatusDaTarefa status_da_tarefa;

    @ManyToMany(mappedBy = "tarefas")
    private List<Usuario> usuario = new ArrayList<>();


    public Tarefas (CadastraTarefa dados){
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.data_de_adicao = LocalDate.now();
        this.status_da_tarefa = dados.status();
    }

    public Tarefas (AtualizaTarefaDTO dados){
        this.tarefas_id = dados.id();
        this.nome = dados.nome();
        this.descricao = dados.descricao();
    }
}
