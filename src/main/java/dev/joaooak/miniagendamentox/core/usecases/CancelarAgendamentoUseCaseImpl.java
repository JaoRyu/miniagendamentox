package dev.joaooak.miniagendamentox.core.usecases;

import dev.joaooak.miniagendamentox.core.entities.Agendamento;
import dev.joaooak.miniagendamentox.core.gateway.AgendamentoGateway;

public class CancelarAgendamentoUseCaseImpl implements CancelarAgendamentoUseCase{

    private final AgendamentoGateway agendamentoGateway;

    public CancelarAgendamentoUseCaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public Agendamento execute(Long id) {

        if(agendamentoGateway.buscarAgendamentoPorId(id) !=null){
            throw new IllegalArgumentException("Agendamento não encontrado");
        }
        return agendamentoGateway.cancelarAgendamento(id);
    }
}
