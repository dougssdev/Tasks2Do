package com.br.tasks2do.repository;

import com.br.tasks2do.model.tarefas.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Integer> {


    @Query("""
            select t from Tarefas t
            JOIN
            t.usuario u 
            WHERE
            u.usuario_id = :id
            """)
    List<Tarefas> findByUsuario(@Param("id") Integer userId);

    @Query("""
            select t.tarefas_id from Tarefas t
            JOIN
            t.usuario u 
            WHERE
            u.usuario_id = :id
            """)
    List<Integer> findTarefaIdByUsuario(@Param("id") Integer userId);


}


