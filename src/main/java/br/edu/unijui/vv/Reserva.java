package br.edu.unijui.vv;

import java.time.LocalDateTime;

public final class Reserva {
    public enum Status { ATIVA, CANCELADA }

    private final String sala;
    private final LocalDateTime inicio;
    private final int minutos;
    private Status status = Status.ATIVA;

    public Reserva(String sala, LocalDateTime inicio, int minutos) {
        if (minutos < 30 || minutos > 120) {
            throw new IllegalArgumentException("Duracao fora dos limites");
        }
        this.sala = sala;
        this.inicio = inicio;
        this.minutos = minutos;
    }

    public void cancelar() {
        if (status != Status.ATIVA) {
            throw new IllegalStateException("Reserva ja cancelada");
        }
        status = Status.CANCELADA;
    }

    public String getSala() { return sala; }
    public LocalDateTime getInicio() { return inicio; }
    public int getMinutos() { return minutos; }
    public Status getStatus() { return status; }
}
