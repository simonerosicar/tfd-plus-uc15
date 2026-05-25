package tfd_sistem;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Motorista {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String telefone;
    private String cnh;
    private String categoriaCnh;
    private LocalDate validadeCnh;
    private String pisPasep;

    @Enumerated(EnumType.STRING)
    private TipoVinculo tipoVinculo; // CONCURSADO ou CONTRATADO

    private boolean ativo = true;
}