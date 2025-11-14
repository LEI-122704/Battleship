package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FrigateTest {

    @Test
    @DisplayName("getSize deve devolver 4")
    void testGetSize() {
        Frigate f = new Frigate(Compass.NORTH, new Position(0, 0));
        assertEquals(4, f.getSize());
    }

    @Test
    @DisplayName("Construtor deve lançar NullPointerException se bearing for null")
    void testNullBearing() {
        assertThrows(AssertionError.class,
                () -> new Frigate(null, new Position(0, 0)));
    }

    // REGION: NORTH
    @Test
    @DisplayName("Frigate orientada a NORTH cria 4 posições verticais para baixo")
    void testNorthPositions() {
        Position start = new Position(2, 3);
        Frigate f = new Frigate(Compass.NORTH, start);

        List<IPosition> pos = f.getPositions();

        assertEquals(4, pos.size());
        assertEquals(new Position(2, 3), pos.get(0));
        assertEquals(new Position(3, 3), pos.get(1));
        assertEquals(new Position(4, 3), pos.get(2));
        assertEquals(new Position(5, 3), pos.get(3));
    }

    // SOUTH
    @Test
    @DisplayName("Frigate orientada a SOUTH cria 4 posições verticais para baixo")
    void testSouthPositions() {
        Position start = new Position(1, 1);
        Frigate f = new Frigate(Compass.SOUTH, start);

        List<IPosition> pos = f.getPositions();

        assertEquals(4, pos.size());
        assertEquals(new Position(1, 1), pos.get(0));
        assertEquals(new Position(2, 1), pos.get(1));
        assertEquals(new Position(3, 1), pos.get(2));
        assertEquals(new Position(4, 1), pos.get(3));
    }

    // EAST
    @Test
    @DisplayName("Frigate orientada a EAST cria 4 posições horizontais à direita")
    void testEastPositions() {
        Position start = new Position(4, 4);
        Frigate f = new Frigate(Compass.EAST, start);

        List<IPosition> pos = f.getPositions();

        assertEquals(4, pos.size());
        assertEquals(new Position(4, 4), pos.get(0));
        assertEquals(new Position(4, 5), pos.get(1));
        assertEquals(new Position(4, 6), pos.get(2));
        assertEquals(new Position(4, 7), pos.get(3));
    }

    // WEST
    @Test
    @DisplayName("Frigate orientada a WEST cria 4 posições horizontais à direita (mesmo código EAST/WEST)")
    void testWestPositions() {
        Position start = new Position(3, 2);
        Frigate f = new Frigate(Compass.WEST, start);

        List<IPosition> pos = f.getPositions();

        assertEquals(4, pos.size());
        assertEquals(new Position(3, 2), pos.get(0));
        assertEquals(new Position(3, 3), pos.get(1));
        assertEquals(new Position(3, 4), pos.get(2));
        assertEquals(new Position(3, 5), pos.get(3));
    }
}
