package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de instrução para a classe Caravel")
class CaravelTest {

    private IPosition startPos;

    @BeforeEach
    void setUp() {
        startPos = new Position(0, 0);
    }

    @Nested
    @DisplayName("Testes de Inicialização")
    class InitializationTests {

        @Test
        @DisplayName("Deve criar Caravel com direção NORTH e posições corretas")
        void testInitializationNorth() {
            Caravel caravel = new Caravel(Compass.NORTH, startPos);
            assertNotNull(caravel);
            assertEquals(Compass.NORTH, caravel.getBearing());
            assertEquals(2, caravel.getPositions().size());
            assertEquals(new Position(0, 0), caravel.getPositions().get(0));
            assertEquals(new Position(1, 0), caravel.getPositions().get(1));
        }

        @Test
        @DisplayName("Deve criar Caravel com direção EAST e posições corretas")
        void testInitializationEast() {
            Caravel caravel = new Caravel(Compass.EAST, startPos);
            assertEquals(2, caravel.getPositions().size());
            assertEquals(new Position(0, 0), caravel.getPositions().get(0));
            assertEquals(new Position(0, 1), caravel.getPositions().get(1));
        }

        @Test
        @DisplayName("Deve criar Caravel com direção SOUTH e posições corretas")
        void testInitializationSouth() {
            Caravel caravel = new Caravel(Compass.SOUTH, startPos);
            assertEquals(2, caravel.getPositions().size());
            assertEquals(new Position(0, 0), caravel.getPositions().get(0));
            assertEquals(new Position(1, 0), caravel.getPositions().get(1));
        }

        @Test
        @DisplayName("Deve criar Caravel com direção WEST e posições corretas")
        void testInitializationWest() {
            Caravel caravel = new Caravel(Compass.WEST, startPos);
            assertEquals(2, caravel.getPositions().size());
            assertEquals(new Position(0, 0), caravel.getPositions().get(0));
            assertEquals(new Position(0, 1), caravel.getPositions().get(1));
        }
    }

    @Nested
    @DisplayName("Testes de tamanho")
    class SizeTests {

        @Test
        @DisplayName("Deve retornar tamanho correto")
        void testSize() {
            Caravel caravel = new Caravel(Compass.SOUTH, startPos);
            assertEquals(2, caravel.getSize());
        }
    }

    @Nested
    @DisplayName("Testes de Exceções")
    class ExceptionTests {

        @Test
        @DisplayName("Deve lançar NullPointerException ao passar direção nula")
        void testNullBearing() {
            assertThrows(AssertionError.class, () -> new Caravel(null, startPos));
        }
    }
}
