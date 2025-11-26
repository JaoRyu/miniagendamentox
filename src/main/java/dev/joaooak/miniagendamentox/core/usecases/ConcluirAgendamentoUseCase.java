package dev.joaooak.miniagendamentox.core.usecases;

import dev.joaooak.miniagendamentox.core.entities.Agendamento;

public interface ConcluirAgendamentoUseCase {

    Agendamento execute(Long id);
}
