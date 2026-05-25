package tfd_sistem;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificação SISREG
    private String nome;
    private String cpf;
    private String cns;
    private LocalDate dataNascimento;
    private String nomeMae;
    private String nacionalidade;

    // Contatos (UX: Emergência)
    private String telefone;
    private String nomeContatoRecado;
    private String telefoneRecado;

    // Endereço Detalhado
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String pontoReferencia;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private NecessidadeEspecial necessidadeEspecial = NecessidadeEspecial.NENHUMA;

    private boolean ativo = true;
}