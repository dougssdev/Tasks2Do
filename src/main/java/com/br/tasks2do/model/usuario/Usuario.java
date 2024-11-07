package com.br.tasks2do.model.usuario;

import com.br.tasks2do.model.tarefas.Tarefas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity (name = "Usuario")
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String senha;
    private String email;

    @OneToMany (mappedBy = "usuario")
    private List<Tarefas> tarefasList;
}
