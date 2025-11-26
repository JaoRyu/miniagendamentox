package dev.joaooak.miniagendamentox.infrastructure.mapper;


import dev.joaooak.miniagendamentox.core.entities.Agendamento;
import dev.joaooak.miniagendamentox.core.enums.StatusAgendamento;
import dev.joaooak.miniagendamentox.infrastructure.persistence.AgendamentoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoEntityMapper {

    public AgendamentoEntity toEntity(Agendamento agendamento){
        return  AgendamentoEntity.builder()
                .titulo(agendamento.titulo())
                .descricao(agendamento.descricao())
                .dataInicio(agendamento.dataInicio())
                .dataFim(agendamento.dataFim())
                .usuario(agendamento.usuario())
                .status(StatusAgendamento.AGENDADO)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .usuario(agendamento.usuario())
                .build();
    }

    public Agendamento toDomain(AgendamentoEntity agendamentoEntity){
        return new Agendamento(
                agendamentoEntity.getId(),
                agendamentoEntity.getTitulo(),
                agendamentoEntity.getDescricao(),
                agendamentoEntity.getDataInicio(),
                agendamentoEntity.getDataFim(),
                agendamentoEntity.getStatus(),
                agendamentoEntity.getUsuario(),
                agendamentoEntity.getCriadoEm(),
                agendamentoEntity.getAtualizadoEm()
        );
    }
}
