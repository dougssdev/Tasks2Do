package com.br.tasks2do.model.usuario;

import com.br.tasks2do.dto.RegisterRequestDTO;
import com.br.tasks2do.model.tarefas.Tarefas;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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
    private int usuario_id;

    private String login;
    private String senha;

    @ManyToMany
    @JoinTable(
            name = "usuario_tarefa",
            joinColumns = {@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")},
            inverseJoinColumns = {@JoinColumn(name = "tarefas_id", referencedColumnName = "tarefas_id")}
    )
    private List<Tarefas> tarefas;

    public Usuario(CadastroUsuario user) {
        this.login = user.login();
        this.senha = user.senha();
    }

    public Usuario(RegisterRequestDTO dados) {
        this.login = dados.login();
        this.senha = dados.senha();
    }
}

