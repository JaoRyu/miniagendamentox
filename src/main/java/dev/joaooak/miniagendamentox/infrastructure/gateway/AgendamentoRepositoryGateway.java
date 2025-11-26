package dev.joaooak.miniagendamentox.infrastructure.gateway;

import dev.joaooak.miniagendamentox.core.entities.Agendamento;
import dev.joaooak.miniagendamentox.core.enums.StatusAgendamento;
import dev.joaooak.miniagendamentox.core.gateway.AgendamentoGateway;
import dev.joaooak.miniagendamentox.infrastructure.mapper.AgendamentoEntityMapper;
import dev.joaooak.miniagendamentox.infrastructure.persistence.AgendamentoEntity;
import dev.joaooak.miniagendamentox.infrastructure.persistence.AgendamentoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoRepositoryGateway implements AgendamentoGateway {

    private final AgendamentoRepository repository;
    private final AgendamentoEntityMapper mapper;

    public AgendamentoRepositoryGateway(AgendamentoRepository repository, AgendamentoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Agendamento criarAgendamento(Agendamento agendamento) {
        validarIntervalo(agendamento.dataInicio(), agendamento.dataFim());

        AgendamentoEntity agendamentoEntity = repository.save(mapper.toEntity(agendamento));
        return mapper.toDomain(agendamentoEntity);
    }

    @Override
    public Agendamento buscarAgendamentoPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        return mapper.toDomain(repository.save(mapper.toEntity(agendamento)));
    }

    @Override
    public Agendamento cancelarAgendamento(Long id) {
        var existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        existente.setStatus(StatusAgendamento.CANCELADO);
        existente.setAtualizadoEm(LocalDateTime.now());

        return mapper.toDomain(repository.save(existente));
    }

    @Override
    public Agendamento concluirAgendamento(Long id) {
        var existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        existente.setStatus(StatusAgendamento.CONCLUIDO);
        existente.setAtualizadoEm(LocalDateTime.now());

        return mapper.toDomain(repository.save(existente));
    }

    private void validarIntervalo(LocalDateTime inicio, LocalDateTime fim){
        if(inicio==null || fim ==null || !inicio.isBefore(fim)){
            throw new IllegalArgumentException("Intervalo inválido");
        }
    }

    private void checarConflito(String usuario, LocalDateTime inicio, LocalDateTime fim, Long ignoreId){
        boolean conflito = repository.existsConflict(usuario, inicio, fim, ignoreId);
        if(conflito){
            throw new IllegalArgumentException("Conflito de agenda!");
        }
    }
}
