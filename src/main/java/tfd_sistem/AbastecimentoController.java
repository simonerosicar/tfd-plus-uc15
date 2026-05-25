package tfd_sistem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/abastecimentos")
public class AbastecimentoController {

    @Autowired
    private AbastecimentoService service;

    @Autowired
    private AbastecimentoRepository repository;

    // ⛽ Registra o abastecimento e já atualiza o KM da Van automaticamente!
    @PostMapping
    public Abastecimento registrar(@RequestBody Abastecimento abastecimento) {
        return service.registrar(abastecimento);
    }

    // 📊 Lista o histórico para o financeiro da prefeitura conferir as notas
    @GetMapping
    public List<Abastecimento> listarHistorico() {
        return repository.findAll();
    }
}