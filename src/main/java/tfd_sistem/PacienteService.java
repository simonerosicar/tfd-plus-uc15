package tfd_sistem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    // --- 1. SALVAR NOVO PACIENTE ---
    public Paciente salvarPaciente(Paciente paciente) {

        // Regra: Bloqueio de CNS Duplicado
        if (paciente.getCns() != null) {
            Optional<Paciente> existente = repository.findByCns(paciente.getCns());
            if (existente.isPresent() && !existente.get().getId().equals(paciente.getId())) {
                throw new RuntimeException("Olá! Este número de cartão SUS (CNS) já está cadastrado no sistema. Por favor, pesquise pelo paciente para evitar cadastros duplicados.");
            }
        }

        // Regra: Trava de Homônimos (Nomes Iguais)
        if (paciente.getNome() != null && paciente.getId() == null) {
            List<Paciente> homonimos = repository.findByNomeIgnoreCase(paciente.getNome());
            if (!homonimos.isEmpty()) {
                throw new RuntimeException("Aviso do Sistema: Já existe um paciente com o nome '" + paciente.getNome() + "'. Verifique o CNS e o nome da mãe para garantir que não é a mesma pessoa antes de criar um novo cadastro.");
            }
        }

        return repository.save(paciente);
    }

    // --- 2. ATUALIZAR PACIENTE EXISTENTE (Com Auditoria) ---
    public Paciente atualizarPaciente(Long id, Paciente dadosAtualizados) {

        // 1. Buscar paciente no banco
        Paciente pacienteExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aviso: Paciente não encontrado com o ID " + id));

        // 2. Guardar dados antigos (Para a Auditoria)
        String nomeAntigo = pacienteExistente.getNome();
        String telefoneAntigo = pacienteExistente.getTelefone();

        // 3. Validar duplicidade de forma inteligente
        boolean duplicado = repository.existsByNomeAndTelefone(
                dadosAtualizados.getNome(),
                dadosAtualizados.getTelefone()
        );

        if (duplicado &&
                (nomeAntigo == null || !nomeAntigo.equals(dadosAtualizados.getNome()) ||
                        telefoneAntigo == null || !telefoneAntigo.equals(dadosAtualizados.getTelefone()))) {

            throw new RuntimeException("Aviso do Sistema: Já existe outro paciente com esses mesmos dados (Nome e Telefone). Operação cancelada para evitar duplicação.");
        }

        // 4. Atualizar os dados
        pacienteExistente.setNome(dadosAtualizados.getNome());
        pacienteExistente.setTelefone(dadosAtualizados.getTelefone());
        pacienteExistente.setCns(dadosAtualizados.getCns());
        pacienteExistente.setNecessidadeEspecial(dadosAtualizados.getNecessidadeEspecial());

        // 5. Auditoria detalhada (Gravada no console/logs)
        System.out.println("\n--- AUDITORIA: ATUALIZAÇÃO DE PACIENTE ---");
        System.out.println("Paciente ID: " + id);

        if (nomeAntigo != null && !nomeAntigo.equals(dadosAtualizados.getNome())) {
            System.out.println("NOME alterado: " + nomeAntigo + " -> " + dadosAtualizados.getNome());
        }

        if (telefoneAntigo != null && !telefoneAntigo.equals(dadosAtualizados.getTelefone())) {
            System.out.println("TELEFONE alterado: " + telefoneAntigo + " -> " + dadosAtualizados.getTelefone());
        }
        System.out.println("------------------------------------------\n");

        // 6. Salva as modificações
        return repository.save(pacienteExistente);
    }

    // --- 3. LISTAR TODOS ---
    public List<Paciente> listarTodos() {
        return repository.findAll();
    }
}