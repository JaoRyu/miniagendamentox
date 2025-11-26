package dev.joaooak.miniagendamentox.infrastructure.presentation;


import dev.joaooak.miniagendamentox.core.entities.Agendamento;
import dev.joaooak.miniagendamentox.core.usecases.*;
import dev.joaooak.miniagendamentox.infrastructure.dto.AgendamentoCreateRequest;
import dev.joaooak.miniagendamentox.infrastructure.dto.AgendamentoResponse;
import dev.joaooak.miniagendamentox.infrastructure.dto.AgendamentoUpdateRequest;
import dev.joaooak.miniagendamentox.infrastructure.mapper.AgendamentoCreateMapper;
import dev.joaooak.miniagendamentox.infrastructure.mapper.AgendamentoResponseMapper;
import dev.joaooak.miniagendamentox.infrastructure.mapper.AgendamentoUpdateMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("api/agendamentos")
public class AgendamentoController {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase;
    private final AtualizarAgendamentoUseCase atualizarAgendamentoUseCase;
    private final CancelarAgendamentoUseCase cancelarAgendamentoUseCase;
    private final ConcluirAgendamentoUseCase concluirAgendamentoUseCase;
    private final AgendamentoCreateMapper agendamentoCreateMapper;
    private final AgendamentoResponseMapper agendamentoResponseMapper;
    private final AgendamentoUpdateMapper agendamentoUpdateMapper;

    public AgendamentoController(CriarAgendamentoUseCase criarAgendamentoUseCase, BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase, AtualizarAgendamentoUseCase atualizarAgendamentoUseCase, CancelarAgendamentoUseCase cancelarAgendamentoUseCase, ConcluirAgendamentoUseCase concluirAgendamentoUseCase, AgendamentoCreateMapper agendamentoCreateMapper, AgendamentoResponseMapper agendamentoResponseMapper, AgendamentoUpdateMapper agendamentoUpdateMapper) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.buscarAgendamentoPorIdUseCase = buscarAgendamentoPorIdUseCase;
        this.atualizarAgendamentoUseCase = atualizarAgendamentoUseCase;
        this.cancelarAgendamentoUseCase = cancelarAgendamentoUseCase;
        this.concluirAgendamentoUseCase = concluirAgendamentoUseCase;
        this.agendamentoCreateMapper = agendamentoCreateMapper;
        this.agendamentoResponseMapper = agendamentoResponseMapper;
        this.agendamentoUpdateMapper = agendamentoUpdateMapper;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criarAgendamento(@RequestBody AgendamentoCreateRequest request) {
        Agendamento criado = criarAgendamentoUseCase.execute(agendamentoCreateMapper.toEntity(request));

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", agendamentoResponseMapper.toDto(criado));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoResponseMapper.toDto(buscarAgendamentoPorIdUseCase.execute(id)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarAgendamento(@PathVariable Long id, @RequestBody AgendamentoUpdateRequest request) {
        Agendamento existente = buscarAgendamentoPorIdUseCase.execute(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento atualizado = atualizarAgendamentoUseCase.execute(agendamentoUpdateMapper.merge(existente, request));
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento atualizado com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toDto(atualizado));
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Map<String, Object>> cancelarAgendamento(@PathVariable Long id) {
        Agendamento existente = buscarAgendamentoPorIdUseCase.execute(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento cancelado = cancelarAgendamentoUseCase.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento cancelado com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toDto(cancelado));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> concluirAgendamento(@PathVariable Long id) {
        Agendamento existente = buscarAgendamentoPorIdUseCase.execute(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento concluido = cancelarAgendamentoUseCase.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento cancelado com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toDto(concluido));
        return ResponseEntity.ok(response);
    }
}
