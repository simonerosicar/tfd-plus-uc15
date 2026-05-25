package tfd_sistem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbastecimentoService {

    @Autowired private AbastecimentoRepository repository;
    @Autowired private VeiculoRepository veiculoRepo;

    public Abastecimento registrar(Abastecimento abastecimento) {
        // 1. Atualiza o KM atual do veículo automaticamente
        Veiculo v = abastecimento.getVeiculo();
        v.setKmAtual(abastecimento.getKmNoAbastecimento());
        veiculoRepo.save(v);

        // 2. Salva o registro de abastecimento
        return repository.save(abastecimento);
    }
}