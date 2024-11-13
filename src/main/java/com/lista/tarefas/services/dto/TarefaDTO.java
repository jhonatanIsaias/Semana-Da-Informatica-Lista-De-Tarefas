package com.lista.tarefas.services.dto;



import com.lista.tarefas.models.TarefaModel;

import java.time.LocalDateTime;

public record TarefaDTO (
        Long id,
        String titulo,
        String descricao,
        boolean concluida,
        LocalDateTime dataCriacao,
        String dataVencimento
)
{

    public TarefaDTO(TarefaModel model){
        this(model.getId(),
                model.getTitulo(),
                model.getDescricao(),
                model.isConcluida(),
                model.getDataCriacao(),
                model.getDataVencimento().toString());
    }

}
