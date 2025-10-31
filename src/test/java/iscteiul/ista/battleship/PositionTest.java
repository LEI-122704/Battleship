package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Nested
    @DisplayName("Cobertura completa de instrução - Position")
    class InstructionCoverageTests {

        @Test
        @DisplayName("Construtor, getRow e getColumn executam corretamente")
        void testConstructorAndGetters() {
            Position p = new Position(2, 3);
            assertEquals(2, p.getRow());
            assertEquals(3, p.getColumn());
        }

        @Test
        @DisplayName("equals() cobre todos os caminhos possíveis")
        void testEqualsAllPaths() {
            Position p1 = new Position(1, 1);

            // Caminho 1: this == other
            assertTrue(p1.equals(p1));

            // Caminho 2a: instanceof true, mesma posição (retorna true)
            Position same = new Position(1, 1);
            assertTrue(p1.equals(same));

            // Caminho 2b: instanceof true, posição diferente (retorna false)
            Position diff = new Position(2, 3);
            assertFalse(p1.equals(diff));

            // Caminho 3: instanceof false (outro tipo)
            assertFalse(p1.equals("not a position"));

            // Caminho 4: null
            assertFalse(p1.equals(null));
        }

        @Test
        @DisplayName("hashCode() executa e gera valor consistente")
        void testHashCode() {
            Position p1 = new Position(2, 3);
            Position p2 = new Position(2, 3);
            assertEquals(p1.hashCode(), p2.hashCode());
        }

        @Test
        @DisplayName("isAdjacentTo() cobre todas as combinações de comparações")
        void testIsAdjacentToAllCombinations() {
            Position p = new Position(5, 5);

            // Ambos verdadeiros -> true
            assertTrue(p.isAdjacentTo(new Position(6, 6)));

            // Esquerda verdadeira, direita falsa -> false
            assertFalse(p.isAdjacentTo(new Position(6, 8)));

            // Esquerda falsa, direita verdadeira -> false
            assertFalse(p.isAdjacentTo(new Position(8, 5)));
        }

        @Test
        @DisplayName("occupy() muda o estado de isOccupied para true")
        void testOccupy() {
            Position p = new Position(1, 1);
            assertFalse(p.isOccupied());
            p.occupy();
            assertTrue(p.isOccupied());
        }

        @Test
        @DisplayName("shoot() muda o estado de isHit para true")
        void testShoot() {
            Position p = new Position(2, 2);
            assertFalse(p.isHit());
            p.shoot();
            assertTrue(p.isHit());
        }

        @Test
        @DisplayName("toString() retorna formato esperado")
        void testToString() {
            Position p = new Position(4, 4);
            assertEquals("Linha = 4 Coluna = 4", p.toString());
        }
    }
}
