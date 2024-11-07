package com.br.tasks2do.model.tarefas;

import com.br.tasks2do.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity (name = "Tarefas")
@Table (name = "tarefas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefas {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descricao;
    private Date data_de_adicao;
    private StatusDaTarefa status_da_tarefa;

    @ManyToOne
    @JoinColumn (name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    public Tarefas (CadastraTarefa dados){
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.data_de_adicao = dados.data_de_adicao();
        this.status_da_tarefa = StatusDaTarefa.Fazendo;

    }
}
