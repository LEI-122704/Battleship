package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FrigateTest {

    @Test
    @DisplayName("Construtor deve criar 4 posições quando orientada a NORTH")
    void testNorthBearing() {
        IPosition start = new Position(2, 3);
        Frigate f = new Frigate(Compass.NORTH, start);

        assertEquals(4, f.getPositions().size());
        assertEquals(new Position(2, 3), f.getPositions().get(0));
        assertEquals(new Position(3, 3), f.getPositions().get(1));
        assertEquals(new Position(4, 3), f.getPositions().get(2));
        assertEquals(new Position(5, 3), f.getPositions().get(3));
    }

    @Test
    @DisplayName("Construtor deve criar 4 posições quando orientada a EAST")
    void testEastBearing() {
        IPosition start = new Position(5, 5);
        Frigate f = new Frigate(Compass.EAST, start);

        assertEquals(4, f.getPositions().size());
        assertEquals(new Position(5, 5), f.getPositions().get(0));
        assertEquals(new Position(5, 6), f.getPositions().get(1));
        assertEquals(new Position(5, 7), f.getPositions().get(2));
        assertEquals(new Position(5, 8), f.getPositions().get(3));
    }

    @Test
    @DisplayName("getSize deve devolver 4")
    void testGetSize() {
        Frigate f = new Frigate(Compass.NORTH, new Position(0, 0));
        assertEquals(4, f.getSize());
    }
}