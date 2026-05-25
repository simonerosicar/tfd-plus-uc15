package tfd_sistem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @Autowired
    private AgendamentoRepository repository;

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento ag) {
        return service.salvar(ag);
    }

    @PutMapping("/{id}/vincular-viagem/{viagemId}")
    public Agendamento vincular(@PathVariable Long id, @PathVariable Long viagemId) {
        return service.vincularViagem(id, viagemId);
    }

    @GetMapping("/painel-frota")
    public List<Map<String, Object>> painel() {
        List<Map<String, Object>> lista = new ArrayList<>();
        repository.findAll().forEach(ag -> {
            Map<String, Object> mapa = new HashMap<>();

            // 👇 O SEU CINTO DE SEGURANÇA ENTRA AQUI 👇
            if (ag.getPaciente() != null) {
                mapa.put("paciente", ag.getPaciente().getNome());
                mapa.put("ponto_referencia", ag.getPaciente().getPontoReferencia());
            } else {
                mapa.put("paciente", "SEM PACIENTE");
                mapa.put("ponto_referencia", "N/A");
            }

            mapa.put("local", ag.getUnidadeExecutante());
            mapa.put("ajuda", sinalizar(ag.getPaciente().getNecessidadeEspecial()));
            lista.add(mapa);
        });
        return lista;
    }

    // Rota para o "AvisoMais" (Confirmar que a pessoa recebeu o recado)
    @PatchMapping("/{id}/confirmar-aviso")
    public Agendamento confirmarAviso(@PathVariable Long id) {
        return service.marcarComoAvisado(id);
    }

    private String sinalizar(NecessidadeEspecial n) {
        return switch (n) {
            case ACAMADO -> "🔴 AMBULÂNCIA";
            case CADEIRANTE -> "🔵 ADAPTADO";
            default -> "⚪ COMUM";
        };
    }

    // ⭐ PAINEL UX: Mostra só quem viaja AMANHÃ e ainda não foi avisado (Retornando o Objeto Puro)
    @GetMapping("/pendentes-aviso")
    public List<Agendamento> painelDeAvisos() {

        LocalDate amanha = LocalDate.now().plusDays(1);
        LocalDateTime inicioAmanha = amanha.atStartOfDay();
        LocalDateTime fimAmanha = amanha.atTime(23, 59, 59);

        // A mágica acontece em uma linha só: vai no banco, filtra e já devolve a lista pura!
        return repository.findByStatusAndViagemDataSaidaBetween(
                StatusAgendamento.PENDENTE,
                inicioAmanha,
                fimAmanha
        );
    }
}