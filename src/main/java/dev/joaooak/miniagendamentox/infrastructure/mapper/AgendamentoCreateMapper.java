package dev.joaooak.miniagendamentox.infrastructure.mapper;


import dev.joaooak.miniagendamentox.core.entities.Agendamento;
import dev.joaooak.miniagendamentox.core.enums.StatusAgendamento;
import dev.joaooak.miniagendamentox.infrastructure.dto.AgendamentoCreateRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoCreateMapper {

    public AgendamentoCreateRequest toDto(Agendamento agendamento){
        return new AgendamentoCreateRequest(
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim(),
                agendamento.usuario()
        );
    }

    public Agendamento toEntity(AgendamentoCreateRequest agendamentoCreateRequest){
        return new Agendamento(
                null,
                agendamentoCreateRequest.titulo(),
                agendamentoCreateRequest.descricao(),
                agendamentoCreateRequest.dataInicio(),
                agendamentoCreateRequest.dataFim(),
                StatusAgendamento.AGENDADO,
                agendamentoCreateRequest.usuario(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
