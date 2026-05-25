package tfd_sistem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tfd_sistem.Paciente;
import tfd_sistem.PacienteRepository;

import java.util.List;

@RestController
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping("/pacientes")
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }
}