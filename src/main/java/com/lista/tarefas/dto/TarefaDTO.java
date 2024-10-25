package com.lista.tarefas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {

    private String titulo;

    private String descricao;

    private boolean concluida;

    private LocalDateTime dataCriacao;

    private String dataVencimento;

}
