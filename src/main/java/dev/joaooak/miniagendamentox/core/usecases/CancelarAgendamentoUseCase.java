package dev.joaooak.miniagendamentox.core.usecases;

import dev.joaooak.miniagendamentox.core.entities.Agendamento;

public interface CancelarAgendamentoUseCase {

    Agendamento execute(Long id);
}
