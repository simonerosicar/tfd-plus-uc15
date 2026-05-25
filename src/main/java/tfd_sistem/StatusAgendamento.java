package tfd_sistem;

public enum StatusAgendamento {
    PENDENTE,
    AVISADO,       // 👈 Adicione esta linha aqui!
    CONFIRMADO,
    CANCELADO,
    NAO_LOCALIZADO,
    DEVOLVIDO_REGULACAO,
    URGENTE
}