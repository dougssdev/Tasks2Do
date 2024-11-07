package com.br.tasks2do.repository;

import com.br.tasks2do.model.tarefas.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Integer> {

}
