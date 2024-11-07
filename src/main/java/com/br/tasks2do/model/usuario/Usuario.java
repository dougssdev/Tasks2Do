package com.br.tasks2do.model.usuario;

import com.br.tasks2do.model.tarefas.Tarefas;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Tarefas> tarefasList;

    public Usuario(CadastroUsuario user) {
        this.username = user.username();
        this.email = user.email();
        this.senha = user.senha();
    }
}
