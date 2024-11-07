package com.br.tasks2do.model.tarefas;

import com.br.tasks2do.model.usuario.Usuario;
import com.br.tasks2do.repository.TarefasRepository;
import com.br.tasks2do.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefasService {

    @Autowired
    TarefasRepository tr;

    @Autowired
    UsuarioRepository ur;

    public Tarefas tarefaParaUsuario (int userId, Tarefas tarefas){
        Usuario user = ur.findById(userId)
                            .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        tarefas.setUsuario(user);
        return tr.save(tarefas);
    }
}
