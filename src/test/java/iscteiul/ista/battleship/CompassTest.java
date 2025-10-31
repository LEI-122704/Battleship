package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompassTest {

    @Nested
    @DisplayName("Cobertura de instrução - Compass")
    class InstructionCoverageTests {

        @Test
        @DisplayName("Executa todas as instruções do método getDirection()")
        void testGetDirection() {
            assertEquals('n', Compass.NORTH.getDirection());
            assertEquals('s', Compass.SOUTH.getDirection());
            assertEquals('e', Compass.EAST.getDirection());
            assertEquals('o', Compass.WEST.getDirection());
            assertEquals('u', Compass.UNKNOWN.getDirection());
        }

        @Test
        @DisplayName("Executa todas as instruções do método toString()")
        void testToString() {
            assertEquals("n", Compass.NORTH.toString());
            assertEquals("s", Compass.SOUTH.toString());
            assertEquals("e", Compass.EAST.toString());
            assertEquals("o", Compass.WEST.toString());
            assertEquals("u", Compass.UNKNOWN.toString());
        }

        @Test
        @DisplayName("Executa todos os ramos do método charToCompass()")
        void testCharToCompass() {
            assertEquals(Compass.NORTH, Compass.charToCompass('n'));
            assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
            assertEquals(Compass.EAST, Compass.charToCompass('e'));
            assertEquals(Compass.WEST, Compass.charToCompass('o'));
            assertEquals(Compass.UNKNOWN, Compass.charToCompass('x')); // cobre o default
        }
    }
}
