package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de instrução para a classe Carrack")
class CarrackTest {

    private IPosition startPos;

    @BeforeEach
    void setUp() {
        startPos = new Position(0, 0);
    }

    @Nested
    @DisplayName("Testes de Inicialização")
    class InitializationTests {

        @Test
        @DisplayName("Deve criar Carrack com direção NORTH e posições corretas")
        void testInitializationNorth() {
            Carrack carrack = new Carrack(Compass.NORTH, startPos);
            assertNotNull(carrack);
            assertEquals(Compass.NORTH, carrack.getBearing());
            assertEquals(3, carrack.getPositions().size());
            assertEquals(new Position(0, 0), carrack.getPositions().get(0));
            assertEquals(new Position(1, 0), carrack.getPositions().get(1));
            assertEquals(new Position(2, 0), carrack.getPositions().get(2));
        }

        @Test
        @DisplayName("Deve criar Carrack com direção EAST e posições corretas")
        void testInitializationEast() {
            Carrack carrack = new Carrack(Compass.EAST, startPos);
            assertEquals(3, carrack.getPositions().size());
            assertEquals(new Position(0, 0), carrack.getPositions().get(0));
            assertEquals(new Position(0, 1), carrack.getPositions().get(1));
            assertEquals(new Position(0, 2), carrack.getPositions().get(2));
        }

        @Test
        @DisplayName("Deve criar Carrack com direção SOUTH e posições corretas")
        void testInitializationSouth() {
            Carrack carrack = new Carrack(Compass.SOUTH, startPos);
            assertEquals(3, carrack.getPositions().size());
            assertEquals(new Position(0, 0), carrack.getPositions().get(0));
            assertEquals(new Position(1, 0), carrack.getPositions().get(1));
            assertEquals(new Position(2, 0), carrack.getPositions().get(2));
        }

        @Test
        @DisplayName("Deve criar Carrack com direção WEST e posições corretas")
        void testInitializationWest() {
            Carrack carrack = new Carrack(Compass.WEST, startPos);
            assertEquals(3, carrack.getPositions().size());
            assertEquals(new Position(0, 0), carrack.getPositions().get(0));
            assertEquals(new Position(0, 1), carrack.getPositions().get(1));
            assertEquals(new Position(0, 2), carrack.getPositions().get(2));
        }
    }

    @Nested
    @DisplayName("Testes de Tamanho")
    class SizeTests {

        @Test
        @DisplayName("Deve retornar tamanho correto")
        void testSize() {
            Carrack carrack = new Carrack(Compass.SOUTH, startPos);
            assertEquals(3, carrack.getSize());
        }
    }

    @Nested
    @DisplayName("Testes de Exceções")
    class ExceptionTests {

        @Test
        @DisplayName("Deve lançar NullPointerException ao passar direção nula")
        void testNullBearing() {
            assertThrows(AssertionError.class, () -> new Carrack(null, startPos));
        }
    }
}
