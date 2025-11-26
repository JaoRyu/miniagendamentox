package dev.joaooak.miniagendamentox.core.gateway;

import dev.joaooak.miniagendamentox.core.entities.Agendamento;

public interface AgendamentoGateway {

    Agendamento criarAgendamento(Agendamento agendamento);
    Agendamento buscarAgendamentoPorId(Long id);
    Agendamento atualizarAgendamento(Agendamento agendamento);
    Agendamento cancelarAgendamento(Long id);
    Agendamento concluirAgendamento(Long id);
}
