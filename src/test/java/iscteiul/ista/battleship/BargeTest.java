package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de instrução para a classe Barge")
class BargeTest {

    private Barge barge;
    private IPosition startPos;

    @BeforeEach
    void setUp() {
        startPos = new Position(0, 0);
        barge = new Barge(Compass.NORTH, startPos);
    }

    @Nested
    @DisplayName("Teste de Inicialização")
    class InitTests {

        @Test
        @DisplayName("Barge deve ser criada sem ser nula e com bearing correto")
        void testInitialization() {
            assertNotNull(barge, "Barge não deve ser nula");
            assertEquals(Compass.NORTH, barge.getBearing(), "Direção inicial incorreta");
        }

        @Test
        @DisplayName("Posição inicial deve ser atribuída corretamente")
        void testInitialPositionStored() {
            assertNotNull(barge.getPositions(), "Lista de posições não deve ser nula");
            assertEquals(startPos, barge.getPositions().get(0),
                    "A posição inicial não está correta");
        }
    }

    @Nested
    @DisplayName("Teste de Propriedades do Barge")
    class PropertiesTests {

        @Test
        @DisplayName("Tamanho do Barge deve ser 1")
        void testSize() {
            assertEquals(1, barge.getSize(), "Tamanho do Barge deve ser 1");
        }

        @Test
        @DisplayName("Lista de posições deve conter exatamente 1 posição")
        void testPositionsSize() {
            assertEquals(1, barge.getPositions().size(),
                    "Barge deve ter exatamente 1 posição");
        }
    }
}
