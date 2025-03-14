package com.br.tasks2do.model.service;

import com.br.tasks2do.model.tarefas.Tarefas;
import com.br.tasks2do.model.usuario.Usuario;
import com.br.tasks2do.repository.TarefasRepository;
import com.br.tasks2do.repository.UsuarioRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {


    @Autowired
    UsuarioRepository ur;

    @Autowired
    TarefasRepository tr;

    public Usuario buscaUsuarioLogado(){

        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();

        String username = userLogado.getName();

        return ur.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Tarefas buscaTarefa(Integer id) {
        Tarefas tarefas = tr.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));

        Usuario usuario = buscaUsuarioLogado();

        if (!tarefas.getUsuario().contains(usuario)) {
            throw new RuntimeException("Tarefa não pertence ao usuário.");
        }

        return tarefas;
    }
}
