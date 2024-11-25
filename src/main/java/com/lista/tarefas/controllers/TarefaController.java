package com.lista.tarefas.controllers;

import com.lista.tarefas.models.TarefaModel;
import com.lista.tarefas.services.TarefaService;
import com.lista.tarefas.services.dto.TarefaDTO;
import com.lista.tarefas.services.form.TarefaForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping("/tarefas")
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody TarefaForm tarefa) {
        TarefaDTO novaTarefaModel = tarefaService.criarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefaModel);
    }
    @GetMapping("/tarefas")
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<TarefaDTO> tarefaModels = tarefaService.listarTarefas();
        return  ResponseEntity.status(HttpStatus.OK).body(tarefaModels);
    }

    @PutMapping("/tarefas/{id}")
    public ResponseEntity<TarefaDTO> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaModel tarefaModelAtualizada) {
        TarefaDTO tarefaDto = tarefaService.atualizarTarefa(id, tarefaModelAtualizada);
            return ResponseEntity.status(HttpStatus.OK).body(tarefaDto);
    }

    @DeleteMapping("/tarefas/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
            tarefaService.deletarTarefa(id);
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    @PutMapping("/tarefas/{id}/concluir")
    public ResponseEntity<TarefaDTO> marcarComoConcluida(@PathVariable Long id) {
            TarefaDTO tarefaDTO = tarefaService.marcarComoConcluida(id);
            return  ResponseEntity.ok().body(tarefaDTO);
    }

    @GetMapping("/tarefas/pesquisar")
    public ResponseEntity<List<TarefaDTO>> buscarPorTitulo( String titulo) {
        List<TarefaDTO> tarefas = tarefaService.buscarTarefas(titulo);
        return  ResponseEntity.status(HttpStatus.OK).body(tarefas);
    }


}
