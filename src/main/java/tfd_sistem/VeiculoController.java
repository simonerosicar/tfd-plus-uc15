package tfd_sistem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository repository;

    // Método para cadastrar uma van nova
    @PostMapping
    public Veiculo salvar(@RequestBody Veiculo veiculo) {
        return repository.save(veiculo);
    }

    // Método para ver todos os carros da frota de Içara
    @GetMapping
    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }
}