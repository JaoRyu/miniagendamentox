package dev.joaooak.miniagendamentox.infrastructure.persistence;

import dev.joaooak.miniagendamentox.core.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tb_agendamento")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;
    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAgendamento status;
    @Column(nullable = false, length = 80)
    private String usuario;
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}
