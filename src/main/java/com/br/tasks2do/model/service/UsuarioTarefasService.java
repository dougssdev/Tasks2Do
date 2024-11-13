package com.br.tasks2do.model.service;

import com.br.tasks2do.model.tarefas.Tarefas;
import com.br.tasks2do.model.usuario.Usuario;
import com.br.tasks2do.repository.TarefasRepository;
import com.br.tasks2do.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioTarefasService {

    @Autowired
    UsuarioRepository ur;

    @Autowired
    TarefasRepository tr;

    public Usuario adicionaTarefaUsuario(Integer usuario_id, Integer tarefas_id){
        Usuario usuario = ur.findById(usuario_id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Tarefas tarefas = tr.findById(tarefas_id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));

        usuario.getTarefas().add(tarefas);
        return ur.save(usuario);
    }
}
