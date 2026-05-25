package tfd_sistem;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findByNomeContainingIgnoreCase(String nome);
    List<Paciente> findByTelefoneContaining(String telefone);
    List<Paciente> findByCnsContaining(String cns);

    // 🛡️ BUSCAS PARA AS TRAVAS DE SEGURANÇA
    Optional<Paciente> findByCns(String cns);
    List<Paciente> findByNomeIgnoreCase(String nome);

    // 🛡️ REGRA DE OURO DA EDIÇÃO: Validação de duplicidade
    boolean existsByNomeAndTelefone(String nome, String telefone);
}