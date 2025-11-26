package dev.joaooak.miniagendamentox.infrastructure.beans;

import dev.joaooak.miniagendamentox.core.gateway.AgendamentoGateway;
import dev.joaooak.miniagendamentox.core.usecases.*;
import dev.joaooak.miniagendamentox.infrastructure.gateway.AgendamentoRepositoryGateway;
import dev.joaooak.miniagendamentox.infrastructure.mapper.AgendamentoEntityMapper;
import dev.joaooak.miniagendamentox.infrastructure.persistence.AgendamentoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CriarAgendamentoUseCase criarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new CriarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase(AgendamentoGateway agendamentoGateway) {
        return new BuscarAgendamentoPorIdUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public CancelarAgendamentoUseCase cancelarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new CancelarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public ConcluirAgendamentoUseCase concluirAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new ConcluirAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public AtualizarAgendamentoUseCase atualizarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new AtualizarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public AgendamentoGateway agendamentoGateway(
            AgendamentoRepository repository,
            AgendamentoEntityMapper mapper
    ) {
        return new AgendamentoRepositoryGateway(repository, mapper);
    }

}
