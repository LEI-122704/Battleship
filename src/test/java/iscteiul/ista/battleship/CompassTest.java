package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompassTest {

    @Test
    @DisplayName("Testa se getDirection() retorna o caractere correto para cada direção")
    void getDirection() {
        assertEquals('n', Compass.NORTH.getDirection());
        assertEquals('s', Compass.SOUTH.getDirection());
        assertEquals('e', Compass.EAST.getDirection());
        assertEquals('o', Compass.WEST.getDirection());
        assertEquals('u', Compass.UNKNOWN.getDirection());
    }

    @Test
    @DisplayName("Testa se toString() retorna a representação textual correta")
    void testToString() {
        assertEquals("n", Compass.NORTH.toString());
        assertEquals("s", Compass.SOUTH.toString());
        assertEquals("e", Compass.EAST.toString());
        assertEquals("o", Compass.WEST.toString());
        assertEquals("u", Compass.UNKNOWN.toString());
    }

    @Test
    @DisplayName("Testa o método charToCompass() com caracteres válidos e inválidos")
    void charToCompass() {
        assertEquals(Compass.NORTH, Compass.charToCompass('n'));
        assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        assertEquals(Compass.EAST, Compass.charToCompass('e'));
        assertEquals(Compass.WEST, Compass.charToCompass('o'));
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('x')); // caractere inválido
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('N')); // maiúsculo não tratado
    }

    @Test
    @DisplayName("Testa se o array retornado por values() contém todas as direções na ordem correta")
    void valuesTest() {
        Compass[] values = Compass.values();
        assertEquals(5, values.length);
        assertArrayEquals(
                new Compass[]{Compass.NORTH, Compass.SOUTH, Compass.EAST, Compass.WEST, Compass.UNKNOWN},
                values
        );
    }

    @Test
    @DisplayName("Testa se valueOf() retorna a constante correta para cada nome válido")
    void valueOfTest() {
        assertEquals(Compass.NORTH, Compass.valueOf("NORTH"));
        assertEquals(Compass.SOUTH, Compass.valueOf("SOUTH"));
        assertEquals(Compass.EAST, Compass.valueOf("EAST"));
        assertEquals(Compass.WEST, Compass.valueOf("WEST"));
        assertEquals(Compass.UNKNOWN, Compass.valueOf("UNKNOWN"));
    }
}
