package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GalleonTest {

    @Test
    @DisplayName("getSize() deve devolver 5")
    void testGetSize() {
        Galleon g = new Galleon(Compass.NORTH, new Position(0, 0));
        assertEquals(5, g.getSize());
    }

    @Test
    @DisplayName("Galleon orientado a NORTH deve criar as 5 posições corretas")
    void testNorthPositions() {
        Position start = new Position(2, 3);
        Galleon g = new Galleon(Compass.NORTH, start);

        List<IPosition> pos = g.getPositions();
        assertEquals(5, pos.size());

        assertEquals(new Position(2, 3), pos.get(0));
        assertEquals(new Position(2, 4), pos.get(1));
        assertEquals(new Position(2, 5), pos.get(2));
        assertEquals(new Position(3, 4), pos.get(3));
        assertEquals(new Position(4, 4), pos.get(4));
    }

    @Test
    @DisplayName("Galleon orientado a SOUTH deve criar as 5 posições corretas")
    void testSouthPositions() {
        Position start = new Position(1, 1);
        Galleon g = new Galleon(Compass.SOUTH, start);

        List<IPosition> pos = g.getPositions();
        assertEquals(5, pos.size());

        assertEquals(new Position(1, 1), pos.get(0));
        assertEquals(new Position(2, 1), pos.get(1));
        assertEquals(new Position(3, 0), pos.get(2));
        assertEquals(new Position(3, 1), pos.get(3));
        assertEquals(new Position(3, 2), pos.get(4));
    }

    @Test
    @DisplayName("Galleon orientado a EAST deve criar as 5 posições corretas")
    void testEastPositions() {
        Position start = new Position(4, 4);
        Galleon g = new Galleon(Compass.EAST, start);

        List<IPosition> pos = g.getPositions();
        assertEquals(5, pos.size());

        assertEquals(new Position(4, 4), pos.get(0));
        assertEquals(new Position(5, 2), pos.get(1));
        assertEquals(new Position(5, 3), pos.get(2));
        assertEquals(new Position(5, 4), pos.get(3));
        assertEquals(new Position(6, 4), pos.get(4));
    }

    @Test
    @DisplayName("Galleon orientado a WEST deve criar as 5 posições corretas")
    void testWestPositions() {
        Position start = new Position(3, 2);
        Galleon g = new Galleon(Compass.WEST, start);

        List<IPosition> pos = g.getPositions();
        assertEquals(5, pos.size());

        assertEquals(new Position(3, 2), pos.get(0));
        assertEquals(new Position(4, 2), pos.get(1));
        assertEquals(new Position(4, 3), pos.get(2));
        assertEquals(new Position(4, 4), pos.get(3));
        assertEquals(new Position(5, 2), pos.get(4));
    }
}