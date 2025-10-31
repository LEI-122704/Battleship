package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BargeTest {

    private Barge barge;
    private IPosition startPos;

    @BeforeEach
    void setUp() {
        startPos = new Position(0, 0);
        barge = new Barge(Compass.NORTH, startPos);
    }

    @Test
    @DisplayName("Deve criar Barge corretamente")
    void testInitialization() {
        assertNotNull(barge, "Barge não deve ser nula");
        assertEquals(Compass.NORTH, barge.getBearing(), "Direção inicial incorreta");
    }

    @Test
    @DisplayName("Deve retornar tamanho 1")
    void testSize() {
        assertEquals(1, barge.getSize(), "Tamanho do Barge deve ser 1");
    }

    @Test
    @DisplayName("Deve inicializar corretamente a posição do barco")
    void testPositions() {
        assertNotNull(barge.getPositions(), "Lista de posições não deve ser nula");
        assertEquals(1, barge.getPositions().size(), "Barge deve ter exatamente 1 posição");
        assertEquals(startPos, barge.getPositions().get(0), "A posição inicial não está correta");
    }
}