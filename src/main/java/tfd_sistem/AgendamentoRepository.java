package tfd_sistem;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Ajustado para 'dataHoraExame' que é o nome na sua classe Agendamento
    List<Agendamento> findByDataHoraExameBetweenOrderByDataHoraExameAsc(LocalDateTime inicio, LocalDateTime fim);

    // Ajustado para 'status' (o nome da variável)
    long countByStatus(StatusAgendamento status);

    List<Agendamento> findByStatus(StatusAgendamento status);

    List<Agendamento> findByStatusAndDataHoraExameBetween(StatusAgendamento status, LocalDateTime inicio, LocalDateTime fim);

    // Filtra pelo status E pela data da viagem (viagem.dataSaida)
    List<Agendamento> findByStatusAndViagemDataSaidaBetween(StatusAgendamento status, LocalDateTime inicio, LocalDateTime fim);
}