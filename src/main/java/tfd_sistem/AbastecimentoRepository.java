package tfd_sistem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
    // Aqui o Spring Data JPA já cria pra você:
    // .save() -> Para registrar o abastecimento
    // .findAll() -> Para ver todo o histórico de gastos
    // .findById() -> Para consultar um abastecimento específico
}
