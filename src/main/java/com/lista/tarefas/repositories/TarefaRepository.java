package com.lista.tarefas.repositories;

import com.lista.tarefas.models.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {

    @Query(nativeQuery = true,
            value = """
                select
                    * from Tarefa_Model tar
                    where 
                    (:#{#titulo==null} or upper(tar.titulo) like concat('%', coalesce(upper(:titulo), ''), '%'))
                """)
    public List<TarefaModel> findByTitulo(String titulo);

}
