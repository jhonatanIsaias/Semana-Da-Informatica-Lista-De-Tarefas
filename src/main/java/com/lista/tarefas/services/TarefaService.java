package com.lista.tarefas.services;

import com.lista.tarefas.services.dto.TarefaDTO;
import com.lista.tarefas.models.TarefaModel;
import com.lista.tarefas.services.form.TarefaForm;

import java.util.List;
import java.util.Optional;

public interface TarefaService {
     TarefaDTO criarTarefa(TarefaForm task);
     List<TarefaDTO> listarTarefas();
     Optional<TarefaModel> buscarTarefaPorId(Long id);
     TarefaDTO atualizarTarefa(Long id, TarefaModel tarefaModelAtualizada);
     void deletarTarefa(Long id);
     TarefaDTO marcarComoConcluida(Long id);

     List<TarefaDTO> buscarTarefas(String titulo);
}
