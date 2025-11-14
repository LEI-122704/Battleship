package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import iscteiul.ista.battleship.Fleet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Fleet")
class FleetTest {

    Fleet fleet;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
        // Redirect System.out to capture print output
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void resetOutput() {
        outContent.reset();
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    // --- Helper classes ---
    class FakePosition implements IPosition {
        int row, col;

        FakePosition(int r, int c) { row = r; col = c; }

        public int getRow() { return row; }
        public int getColumn() { return col; }
        public boolean equals(Object o) {
            if (!(o instanceof FakePosition p)) return false;
            return p.row == row && p.col == col;
        }
        public boolean isAdjacentTo(IPosition other) { return false; }
        public void occupy() {}
        public void shoot() {}
        public boolean isOccupied() { return false; }
        public boolean isHit() { return false; }
    }

    class FakeShip implements IShip {
        String category;
        List<IPosition> positions;
        boolean floating = true;

        FakeShip(String category, int row, int col) {
            this.category = category;
            this.positions = List.of(new FakePosition(row, col));
        }

        public String getCategory() { return category; }
        public Integer getSize() { return 1; }
        public List<IPosition> getPositions() { return positions; }
        public IPosition getPosition() { return positions.get(0); }
        public Compass getBearing() { return Compass.NORTH; }
        public boolean stillFloating() { return floating; }
        public int getTopMostPos() { return positions.get(0).getRow(); }
        public int getBottomMostPos() { return positions.get(0).getRow(); }
        public int getLeftMostPos() { return positions.get(0).getColumn(); }
        public int getRightMostPos() { return positions.get(0).getColumn(); }
        public boolean occupies(IPosition pos) { return positions.contains(pos); }
        public boolean tooCloseTo(IShip other) { return false; }
        public boolean tooCloseTo(IPosition pos) { return false; }
        public void shoot(IPosition pos) { floating = false; }

        @Override
        public String toString() {
            IPosition p = positions.get(0);
            return category + "@" + p.getRow() + "," + p.getColumn();
        }
    }

    // --- Nested test classes ---

    @Nested
    @DisplayName("Adicionar navios")
    class AddShipTests {
        @Test
        @DisplayName("Deve adicionar a ship corretamente à fleet")
        void testAddShip() {
            FakeShip s = new FakeShip("Fragata", 4, 4);
            assertTrue(fleet.addShip(s));
            assertSame(s, fleet.getShips().get(0));
        }
    }

    @Nested
    @DisplayName("Obter navios")
    class GetShipsTests {
        @Test
        @DisplayName("Deve obter a lista corretamente")
        void testGetShips() {
            FakeShip s = new FakeShip("Fragata", 4, 4);
            fleet.addShip(s);
            assertEquals(1, fleet.getShips().size());
            assertSame(s, fleet.getShips().get(0));
        }

        @Test
        @DisplayName("Deve obter as ships de acordo com o filtro")
        void testGetShipsLike() {
            FakeShip s = new FakeShip("Fragata", 4, 4);
            fleet.addShip(s);
            FakeShip s2 = new FakeShip("Nau", 8, 7);
            fleet.addShip(s2);

            List<IShip> result = fleet.getShipsLike("Nau");

            assertEquals(1, result.size());
            assertSame(s2, result.get(0));
        }

        @Test
        @DisplayName("Deve retornar ships flutuantes")
        void testGetFloatingShips() {
            FakeShip s = new FakeShip("Fragata", 4, 4);
            FakeShip z = new FakeShip("Nau", 8, 7);

            s.shoot(new FakePosition(2, 2));
            fleet.addShip(s);
            fleet.addShip(z);

            List<IShip> floating = fleet.getFloatingShips();

            assertEquals(1, floating.size());
            assertSame(z, floating.get(0));
        }

        @Test
        @DisplayName("Deve retornar a ship na posição dada")
        void testShipAt() {
            FakeShip s = new FakeShip("Fragata", 4, 4);
            FakeShip z = new FakeShip("Nau", 8, 7);
            fleet.addShip(s);
            fleet.addShip(z);

            assertSame(z, fleet.shipAt(new FakePosition(8, 7)));
        }

        @Test
        @DisplayName("Deve retornar null quando não existe navio na posição dada")
        void testShipAtNull() {
            FakeShip s = new FakeShip("Fragata", 3, 3);
            fleet.addShip(s);

            IPosition empty = new FakePosition(0, 0);

            assertNull(fleet.shipAt(empty));
        }
    }

    @Nested
    @DisplayName("Print methods")
    class PrintTests {

        @Test
        @DisplayName("printAllShips prints all ships")
        void testPrintAllShips() {
            FakeShip s1 = new FakeShip("Fragata", 1, 1);
            FakeShip s2 = new FakeShip("Nau", 2, 2);
            fleet.addShip(s1);
            fleet.addShip(s2);

            fleet.printAllShips();

            String output = outContent.toString();
            assertTrue(output.contains("Fragata@1,1"));
            assertTrue(output.contains("Nau@2,2"));
        }

        @Test
        @DisplayName("printFloatingShips prints only floating ships")
        void testPrintFloatingShips() {
            FakeShip s1 = new FakeShip("Fragata", 1, 1);
            FakeShip s2 = new FakeShip("Nau", 2, 2);
            s2.shoot(new FakePosition(2,2)); // sink s2
            fleet.addShip(s1);
            fleet.addShip(s2);

            fleet.printFloatingShips();

            String output = outContent.toString();

            assertTrue(output.contains("Fragata@1,1"));
            assertFalse(output.contains("Nau@2,2"));
        }

        @Test
        @DisplayName("printShipsByCategory prints only ships of that category")
        void testPrintShipsByCategory() {
            FakeShip s1 = new FakeShip("Fragata", 1, 1);
            FakeShip s2 = new FakeShip("Nau", 2, 2);
            fleet.addShip(s1);
            fleet.addShip(s2);

            fleet.printShipsByCategory("Nau");

            String output = outContent.toString();
            assertTrue(output.contains("Nau"));
            assertFalse(output.contains("Fragata"));
        }

        @Test
        @DisplayName("printStatus prints all relevant ships")
        void testPrintStatus() {
            FakeShip s1 = new FakeShip("Fragata", 1, 1);
            FakeShip s2 = new FakeShip("Nau", 2, 2);
            FakeShip s3 = new FakeShip("Galeao", 3, 3);
            fleet.addShip(s1);
            fleet.addShip(s2);
            fleet.addShip(s3);

            fleet.printStatus();

            String output = outContent.toString();
            assertTrue(output.contains("Fragata@1,1"));
            assertTrue(output.contains("Nau@2,2"));
            assertTrue(output.contains("Galeao@3,3"));
        }
    }
}