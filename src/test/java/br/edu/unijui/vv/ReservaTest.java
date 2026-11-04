package br.edu.unijui.vv;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static br.edu.unijui.vv.Reserva.Status.*;

class ReservaTest {
    private final LocalDateTime inicio = LocalDateTime.of(2026, 11, 13, 19, 30);

    @ParameterizedTest(name = "aceita duracao de {0} minutos")
    @ValueSource(ints = {30, 60, 120})
    void deveAceitarDuracoesValidas(int minutos) {
        var reserva = assertDoesNotThrow(() -> new Reserva("204", inicio, minutos));
        assertEquals(minutos, reserva.getMinutos());
    }

    @ParameterizedTest(name = "rejeita duracao de {0} minutos")
    @ValueSource(ints = {29, 121})
    void deveRejeitarDuracoesInvalidas(int minutos) {
        assertThrows(IllegalArgumentException.class,
                     () -> new Reserva("204", inicio, minutos));
    }

    @Test
    void deveIniciarAtiva() {
        assertEquals(ATIVA, new Reserva("204", inicio, 60).getStatus());
    }

    @Test
    void deveCancelarReservaAtiva() {
        var reserva = new Reserva("204", inicio, 60);
        reserva.cancelar();
        assertEquals(CANCELADA, reserva.getStatus());
        assertEquals("204", reserva.getSala());
        assertEquals(inicio, reserva.getInicio());
    }

    @Test
    void deveRejeitarSegundoCancelamento() {
        var reserva = new Reserva("204", inicio, 60);
        reserva.cancelar();
        assertThrows(IllegalStateException.class, reserva::cancelar);
        assertEquals(CANCELADA, reserva.getStatus());
    }
}
