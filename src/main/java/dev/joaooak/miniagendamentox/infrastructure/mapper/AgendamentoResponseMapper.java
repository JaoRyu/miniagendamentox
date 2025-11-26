package dev.joaooak.miniagendamentox.infrastructure.mapper;

import dev.joaooak.miniagendamentox.core.entities.Agendamento;
import dev.joaooak.miniagendamentox.infrastructure.dto.AgendamentoResponse;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoResponseMapper {

    public AgendamentoResponse toDto(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.id(),
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim(),
                agendamento.status(),
                agendamento.usuario(),
                agendamento.criadoEm(),
                agendamento.atualizadoEm()
        );
    }

    public Agendamento toEntity(AgendamentoResponse agendamentoResponse) {
        return new Agendamento(
                agendamentoResponse.id(),
                agendamentoResponse.titulo(),
                agendamentoResponse.descricao(),
                agendamentoResponse.dataInicio(),
                agendamentoResponse.dataFim(),
                agendamentoResponse.status(),
                agendamentoResponse.usuario(),
                agendamentoResponse.criadoEm(),
                agendamentoResponse.atualizadoEm()
        );
    }
}
