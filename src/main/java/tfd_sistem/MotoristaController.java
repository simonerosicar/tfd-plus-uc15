package tfd_sistem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaRepository repository;

    // Método para cadastrar um motorista novo
    @PostMapping
    public Motorista salvar(@RequestBody Motorista motorista) {
        return repository.save(motorista);
    }

    // Método para listar todos os motoristas cadastrados
    @GetMapping
    public List<Motorista> listarTodos() {
        return repository.findAll();
    }
}