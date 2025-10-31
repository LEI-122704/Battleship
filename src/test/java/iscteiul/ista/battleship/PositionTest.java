package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    @DisplayName("Deve retornar a linha correta através de getRow()")
    void getRow() {
        Position pos = new Position(3, 5);
        assertEquals(3, pos.getRow());
    }

    @Test
    @DisplayName("Deve retornar a coluna correta através de getColumn()")
    void getColumn() {
        Position pos = new Position(3, 5);
        assertEquals(5, pos.getColumn());
    }

    @Test
    @DisplayName("Deve gerar o mesmo hashCode() para posições iguais")
    void testHashCode() {
        Position pos1 = new Position(2, 4);
        Position pos2 = new Position(2, 4);
        assertEquals(pos1.hashCode(), pos2.hashCode());
    }

    @Test
    @DisplayName("Deve retornar verdadeiro em equals() para posições com mesma linha e coluna")
    void testEquals() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(2, 3);
        Position pos3 = new Position(3, 3);

        assertEquals(pos1, pos2);
        assertNotEquals(pos1, pos3);
        assertNotEquals(pos1, null);
        assertNotEquals(pos1, "String qualquer");
    }

    @Test
    @DisplayName("Deve retornar verdadeiro se as posições forem adjacentes (isAdjacentTo)")
    void isAdjacentTo() {
        Position pos = new Position(5, 5);

        // posições adjacentes
        assertTrue(pos.isAdjacentTo(new Position(5, 6)));  // direita
        assertTrue(pos.isAdjacentTo(new Position(4, 5)));  // acima
        assertTrue(pos.isAdjacentTo(new Position(6, 6)));  // diagonal
        assertTrue(pos.isAdjacentTo(new Position(5, 4)));  // esquerda
        assertTrue(pos.isAdjacentTo(new Position(6, 5)));  // abaixo

        // não adjacente
        assertFalse(pos.isAdjacentTo(new Position(7, 7)));
        assertFalse(pos.isAdjacentTo(new Position(8, 5)));
    }

    @Test
    @DisplayName("Deve marcar a posição como ocupada após chamar occupy()")
    void occupy() {
        Position pos = new Position(1, 1);
        assertFalse(pos.isOccupied());
        pos.occupy();
        assertTrue(pos.isOccupied());
    }

    @Test
    @DisplayName("Deve marcar a posição como atingida após chamar shoot()")
    void shoot() {
        Position pos = new Position(2, 2);
        assertFalse(pos.isHit());
        pos.shoot();
        assertTrue(pos.isHit());
    }

    @Test
    @DisplayName("Deve retornar verdadeiro se a posição estiver ocupada")
    void isOccupied() {
        Position pos = new Position(3, 3);
        assertFalse(pos.isOccupied());
        pos.occupy();
        assertTrue(pos.isOccupied());
    }

    @Test
    @DisplayName("Deve retornar verdadeiro se a posição tiver sido atingida")
    void isHit() {
        Position pos = new Position(4, 4);
        assertFalse(pos.isHit());
        pos.shoot();
        assertTrue(pos.isHit());
    }

    @Test
    @DisplayName("Deve retornar a string formatada corretamente em toString()")
    void testToString() {
        Position pos = new Position(7, 8);
        assertEquals("Linha = 7 Coluna = 8", pos.toString());
    }
}
