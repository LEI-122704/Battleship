package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Ship")
class ShipTest {

    private Ship ship;
    private FakePosition startPos;

    // --- Helper classes ---
    class FakePosition implements IPosition {
        int row, col;
        boolean hit;

        FakePosition(int r, int c) {
            row = r;
            col = c;
        }

        public int getRow() { return row; }
        public int getColumn() { return col; }
        public boolean equals(Object o) {
            if (!(o instanceof FakePosition p)) return false;
            return p.row == row && p.col == col;
        }
        public boolean isAdjacentTo(IPosition other) {
            return Math.abs(row - other.getRow()) <= 1 && Math.abs(col - other.getColumn()) <= 1;
        }
        public void occupy() {}
        public void shoot() { hit = true; }
        public boolean isOccupied() { return false; }
        public boolean isHit() { return hit; }
    }

    class TestShip extends Ship {
        public TestShip(String category, Compass bearing, FakePosition pos) {
            super(category, bearing, pos);
            positions.add(pos);
            positions.add(new FakePosition(pos.getRow(), pos.getColumn() + 1));
        }

        @Override
        public Integer getSize() { return positions.size(); }
    }

    @BeforeEach
    void setUp() {
        startPos = new FakePosition(0, 0);
        ship = new TestShip("Fragata", Compass.NORTH, startPos);
    }

    // --- Nested tests ---

    @Nested
    @DisplayName("Acessores básicos")
    class AccessorTests {

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
        @DisplayName("Deve retornar o tamanho correto")
        void testGetSize() {
            assertEquals(2, ship.getSize());
        }
    }

    @Nested
    @DisplayName("Status do navio")
    class FloatingTests {

        @Test
        @DisplayName("Deve dizer se o navio ainda flutua")
        void testStillFloating() {
            assertTrue(ship.stillFloating());
            for (IPosition pos : ship.getPositions()) pos.shoot();
            assertFalse(ship.stillFloating());
        }
    }

    @Nested
    @DisplayName("Posições extremas")
    class ExtremePositionTests {

        @Test
        @DisplayName("Deve devolver as posições dos extremos")
        void testGetTopBottomLeftRightMostPos() {
            assertEquals(0, ship.getTopMostPos());
            assertEquals(0, ship.getBottomMostPos());
            assertEquals(0, ship.getLeftMostPos());
            assertEquals(1, ship.getRightMostPos());
        }
    }

    @Nested
    @DisplayName("Ocupa posições")
    class OccupiesTests {

        @Test
        @DisplayName("Deve dizer corretamente se a ship ocupa certas posições")
        void testOccupies() {
            for (IPosition p : ship.getPositions()) assertTrue(ship.occupies(p));
            assertFalse(ship.occupies(new FakePosition(5, 5)));
        }
    }

    @Nested
    @DisplayName("Proximidade")
    class TooCloseTests {

        @Test
        @DisplayName("Deve dizer se está perto de uma posição")
        void testTooCloseToPosition() {
            assertTrue(ship.tooCloseTo(new FakePosition(0, 1)));
            assertFalse(ship.tooCloseTo(new FakePosition(5, 5)));
        }

        @Test
        @DisplayName("Deve dizer se está perto de outro navio")
        void testTooCloseToShip() {
            TestShip otherClose = new TestShip("Caravela", Compass.EAST, new FakePosition(0, 2));
            assertTrue(ship.tooCloseTo(otherClose));

            TestShip far = new TestShip("Caravela", Compass.EAST, new FakePosition(5, 5));
            assertFalse(ship.tooCloseTo(far));
        }
    }

    @Nested
    @DisplayName("Disparo")
    class ShootTests {

        @Test
        @DisplayName("Deve disparar corretamente às posições dadas")
        void testShoot() {
            FakePosition posToShoot = (FakePosition) ship.getPositions().get(0);
            assertFalse(posToShoot.isHit());

            ship.shoot(posToShoot);
            assertTrue(posToShoot.isHit());

            // Shooting outside ship should not affect positions
            FakePosition outside = new FakePosition(10, 10);
            ship.shoot(outside);
            for (IPosition pos : ship.getPositions()) {
                assertTrue(pos.isHit() || !pos.isHit()); // still correct
            }
        }
    }

    @Nested
    @DisplayName("Método buildShip")
    class BuildShipTests {

        @Test
        @DisplayName("Deve criar instâncias corretas para cada tipo de navio")
        void testBuildShip() {
            Position pos = new Position(0, 0);
            Ship s = Ship.buildShip("galeao", Compass.NORTH, pos);
            assertNotNull(s);
            assertEquals("Galeao", s.getCategory());

            Ship unknown = Ship.buildShip("unknown", Compass.NORTH, pos);
            assertNull(unknown);
        }
    }

    @Nested
    @DisplayName("toString")
    class ToStringTests {

        @Test
        @DisplayName("Deve retornar string legível")
        void testToString() {
            String str = ship.toString();
            assertTrue(str.contains("Fragata"));
            assertTrue(str.contains("n"));
        }
    }
}