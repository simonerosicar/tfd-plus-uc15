package tfd_sistem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired private AgendamentoRepository repository;
    @Autowired private ViagemRepository viagemRepo;

    public Agendamento salvar(Agendamento ag) {
        return repository.save(ag);
    }

    public Agendamento vincularViagem(Long agId, Long viagemId) {
        Agendamento ag = repository.findById(agId).orElseThrow();
        Viagem v = viagemRepo.findById(viagemId).orElseThrow();
        ag.setViagem(v);
        return repository.save(ag);
    }

    // Método para atualizar dados da Maria e do José
    public Agendamento atualizar(Long id, Agendamento novo) {
        Agendamento antigo = repository.findById(id).orElseThrow();
        antigo.setNomeAcompanhante(novo.getNomeAcompanhante());
        antigo.setCpfAcompanhante(novo.getCpfAcompanhante());
        antigo.setUnidadeExecutante(novo.getUnidadeExecutante());
        antigo.setProcedimento(novo.getProcedimento());
        antigo.setDataHoraExame(novo.getDataHoraExame());
        antigo.setStatus(novo.getStatus());
        return repository.save(antigo);
    }

    // ⭐ ACRESCIMO AQUI: A função que o Controller estava procurando!
    public Agendamento marcarComoAvisado(Long id) {
        Agendamento ag = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aviso: Agendamento não encontrado!"));

        // Aqui mudamos o status para AVISADO (Verifique se essa opção existe no seu Enum!)
        ag.setStatus(StatusAgendamento.AVISADO);

        return repository.save(ag);
    }
}