package dev.joaooak.miniagendamentox.core.usecases;

import dev.joaooak.miniagendamentox.core.entities.Agendamento;
import dev.joaooak.miniagendamentox.core.gateway.AgendamentoGateway;

public class CriarAgendamentoUseCaseImpl implements CriarAgendamentoUseCase{

    private final AgendamentoGateway agendamentoGateway;

    public CriarAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public Agendamento execute(Agendamento agendamento) {
        return agendamentoGateway.criarAgendamento(agendamento);
    }
}
