package dev.joaooak.miniagendamentox.core.usecases;

import dev.joaooak.miniagendamentox.core.entities.Agendamento;

public interface BuscarAgendamentoPorIdUseCase {

    Agendamento execute(Long id);
}
