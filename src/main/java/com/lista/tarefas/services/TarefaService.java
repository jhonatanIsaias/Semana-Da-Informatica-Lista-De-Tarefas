package com.lista.tarefas.services;

import com.lista.tarefas.dto.TarefaDTO;
import com.lista.tarefas.entities.Tarefa;
import com.lista.tarefas.repositories.TarefaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public Tarefa criarTarefa(TarefaDTO task) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(task.getTitulo());
        tarefa.setDescricao(task.getDescricao());
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setDataVencimento(LocalDateTime.parse(task.getDataVencimento(), DATE_FORMATTER));
        tarefa.setConcluida(false);

        return tarefaRepository.save(tarefa);
    }
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }
    public Optional<Tarefa> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }
    @Transactional
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaExistente = this.buscarTarefaPorId(id);

        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();
            tarefa.setTitulo(tarefaAtualizada.getTitulo());
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setConcluida(tarefaAtualizada.isConcluida());
            tarefa.setDataVencimento(tarefaAtualizada.getDataVencimento());
            return tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada");
        }
    }

    public void deletarTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tarefa não encontrada");
        }
    }

    @Transactional
    public Tarefa marcarComoConcluida(Long id) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);

        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();
            tarefa.setConcluida(true);
            return tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada");
        }
    }

}
