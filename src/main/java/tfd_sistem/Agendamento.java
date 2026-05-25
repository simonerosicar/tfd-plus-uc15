package tfd_sistem;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dados da Guia SISREG (Essencial para Auditoria)
    private String chaveConfirmacao;
    private String codigoSolicitacao;
    private String procedimento;
    private String unidadeExecutante;
    private LocalDateTime dataHoraExame;

    // Acompanhante (Pode ser trocado na hora da viagem)
    private String nomeAcompanhante;
    private String cpfAcompanhante;

    // Relacionamentos (Os "Pais" do Agendamento)
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status = StatusAgendamento.PENDENTE;
}