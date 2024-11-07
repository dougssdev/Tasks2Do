package com.br.tasks2do.model.tarefas;

import com.br.tasks2do.model.usuario.Usuario;
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
    private Usuario usuario;
}
