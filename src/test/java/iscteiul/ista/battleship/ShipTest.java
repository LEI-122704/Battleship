package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    private Ship ship;
    private FakePosition startPos;

    // Classe Position para testes
    class FakePosition implements IPosition {
        int row, col;
        boolean hit;
        FakePosition(int r, int c) { row = r; col = c; }

        public int getRow() { return row; }
        public int getColumn() { return col; }
        public boolean equals(Object o) {
            if (!(o instanceof FakePosition p)) return false;
            return p.row == row && p.col == col;
        }
        public boolean isAdjacentTo(IPosition other) { return false; }
        public void occupy() {}
        public void shoot() {hit = true;}
        public boolean isOccupied() { return false; }
        public boolean isHit() { return hit; }
    }

    class TestShip extends Ship {
        public TestShip(String category, Compass bearing, FakePosition pos) {
            super(category, bearing, pos);

            positions.add(pos);
            positions.add(new Position(pos.getRow(), pos.getColumn() + 1));
        }

        @Override
        public Integer getSize() {
            return positions.size();
        }
    }

    @BeforeEach
    void setUp() {
        startPos = new FakePosition(0, 0); // assuming you have a Position class
        ship = new TestShip("Fragata", Compass.NORTH, startPos);
    }

    @Test
    @DisplayName("Deve retornar a categoria")
    void testGetCategory() {
        assertEquals("Fragata", ship.getCategory());
    }

    @Test
    @DisplayName("Deve retornar a position da ship")
    void testGetPosition() {
        assertSame(startPos, ship.getPosition());
    }

    @Test
    @DisplayName("Deve retornar a sua bearing")
    void testGetBearing() {
        assertSame(Compass.NORTH, ship.getBearing());
    }

    @Test
    @DisplayName("Deve dizer se: ship is floating")
    void testStillFloating() {
        assertTrue(ship.stillFloating());

        for (IPosition pos : ship.getPositions()) {
            pos.shoot();
        }
        assertFalse(ship.stillFloating());
    }

    @Test
    @DisplayName("Deve devolver as posições dos extremos")
    void testGetTopBottomLeftRightMostPos() {
        assertEquals(0, ship.getTopMostPos());
        assertEquals(0, ship.getBottomMostPos());
        assertEquals(0, ship.getLeftMostPos());
        assertEquals(1, ship.getRightMostPos());
    }

    @Test
    @DisplayName("Deve dizer corretamente se a ship ocupa certas posições")
    void testOccupies() {
        List<IPosition> posList = ship.getPositions();
        for (IPosition p : posList) assertTrue(ship.occupies(p));
        assertFalse(ship.occupies(new FakePosition(5, 5)));
    }

    @Test
    @DisplayName("Deve dizer se está perto ou não de certas posições")
    void tooCloseTo() {
        assertTrue(ship.tooCloseTo(new FakePosition(0, 1)));
        assertFalse(ship.tooCloseTo(new FakePosition(5, 5)));
    }

    @Test
    @DisplayName("Deve dizer se está perto ou não de certas posições")
    void testTooCloseTo() {
        TestShip other = new TestShip("Caravela", Compass.EAST, new FakePosition(0, 2));
        assertTrue(ship.tooCloseTo(other));
        TestShip far = new TestShip("Caravela", Compass.EAST, new FakePosition(5, 5));
        assertFalse(ship.tooCloseTo(far));
    }

    @Test
    @DisplayName("Deve disparar corretamente às posições dadas")
    void testShoot() {
        TestShip other = new TestShip("Caravela", Compass.EAST, new FakePosition(0, 2));
        assertTrue(ship.tooCloseTo(other));
        TestShip far = new TestShip("Caravela", Compass.EAST, new FakePosition(5, 5));
        assertFalse(ship.tooCloseTo(far));
    }
}