package tfd_sistem;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Abastecimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora = LocalDateTime.now();
    private Double kmNoAbastecimento; // O que marca no painel na hora do posto
    private Double litros;            // Quantos litros entraram
    private Double valorTotal;        // Opcional, mas bom para o financeiro
    private String posto;             // Nome do posto ou "Pátio Central"

    @ManyToOne @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne @JoinColumn(name = "motorista_id")
    private Motorista motorista;
}
