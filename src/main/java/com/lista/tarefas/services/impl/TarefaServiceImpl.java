package com.lista.tarefas.services.impl;

import com.lista.tarefas.config.exceptions.NotFoundException;
import com.lista.tarefas.services.dto.TarefaDTO;
import com.lista.tarefas.models.TarefaModel;
import com.lista.tarefas.repositories.TarefaRepository;
import com.lista.tarefas.services.TarefaService;
import com.lista.tarefas.services.form.TarefaForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository tarefaRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    public TarefaDTO criarTarefa(TarefaForm task) {
        TarefaModel tarefaModel = new TarefaModel();
        tarefaModel.setTitulo(task.titulo());
        tarefaModel.setDescricao(task.descricao());
        tarefaModel.setDataCriacao(LocalDateTime.now());
        tarefaModel.setDataVencimento(LocalDateTime.parse(task.dataVencimento(), DATE_FORMATTER));
        tarefaModel.setConcluida(false);

        tarefaRepository.save(tarefaModel);
        return new TarefaDTO(tarefaModel);
    }
    @Override
    public List<TarefaDTO> listarTarefas() {
        return tarefaRepository.findAll().stream().map(TarefaDTO::new).toList();
    }
    @Override
    public Optional<TarefaModel> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }
    @Override
    @Transactional
    public TarefaDTO atualizarTarefa(Long id, TarefaModel tarefaModelAtualizada) {
        TarefaModel tarefaModel = this.buscarTarefaPorId(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));

            tarefaModel.setTitulo(tarefaModelAtualizada.getTitulo());
            tarefaModel.setDescricao(tarefaModelAtualizada.getDescricao());
            tarefaModel.setDataVencimento(tarefaModelAtualizada.getDataVencimento());
            tarefaRepository.save(tarefaModel);
            return new TarefaDTO(tarefaModel);
    }

    @Override
    public void deletarTarefa(Long id) {
            tarefaRepository.deleteById(id);
    }
    @Override
    @Transactional
    public TarefaDTO marcarComoConcluida(Long id) {

        Optional<TarefaModel> tarefaExistente = tarefaRepository.findById(id);

        if (tarefaExistente.isPresent()) {
            TarefaModel tarefaModel = tarefaExistente.get();
            tarefaModel.setConcluida(!tarefaModel.isConcluida());
            tarefaRepository.save(tarefaModel);
            return new TarefaDTO(tarefaModel);
        } else {
            throw new NotFoundException("Tarefa não encontrada");
        }
    }

    @Override
    public List<TarefaDTO> buscarTarefas(String titulo) {
        return tarefaRepository.findByTitulo(titulo).stream().map(TarefaDTO::new).toList();
    }
}
