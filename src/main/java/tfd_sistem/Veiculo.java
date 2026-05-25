package tfd_sistem;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Veiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private String placa;
    private String renavam;
    private int capacidade;
    private double kmAtual;
    private LocalDate proximaRevisao;
    private boolean emManutencao = false;
}