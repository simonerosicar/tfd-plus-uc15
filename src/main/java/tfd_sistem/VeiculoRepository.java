package tfd_sistem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // Aqui a mágica do Spring Data JPA faz tudo sozinha!
}