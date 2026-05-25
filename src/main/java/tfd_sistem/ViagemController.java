package tfd_sistem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemRepository repository;

    @PostMapping
    public Viagem criarViagem(@RequestBody Viagem viagem) {
        return repository.save(viagem);
    }

    @GetMapping
    public List<Viagem> listarViagens() {
        return repository.findAll();
    }
}