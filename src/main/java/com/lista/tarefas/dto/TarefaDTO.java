package com.lista.tarefas.dto;



import java.time.LocalDateTime;

public record TarefaDTO ( String titulo,String descricao,boolean concluida, LocalDateTime dataCriacao, String dataVencimento){}
