package com.lista.tarefas.services.form;

import java.time.LocalDateTime;

public record TarefaForm(
        String titulo,
        String descricao,
        boolean concluida,
        LocalDateTime dataCriacao,
        String dataVencimento
) {
}
