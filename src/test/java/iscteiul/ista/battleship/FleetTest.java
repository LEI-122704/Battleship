package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import iscteiul.ista.battleship.Fleet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Fleet")
class FleetTest {

    Fleet fleet;

    // Classe Position para testes
    static class FakePosition implements IPosition {
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

    // Classe Ship para testes
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
    }


    @BeforeEach
    void setUp() {
        fleet = new Fleet();
    }

    @Test
    @DisplayName("Deve adicionar a ship corretamente à fleet")
    void testAddShip() {
        FakeShip s = new FakeShip("Fragata", 4, 4);
        assertTrue(fleet.addShip(s));
        assertSame(s, fleet.getShips().get(0));
    }

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
        fleet.addShip(s); fleet.addShip(z);

        List<IShip> floating = fleet.getFloatingShips();

        assertEquals(1, floating.size());
        assertSame(z, floating.get(0));
    }

    @Test
    @DisplayName("Deve retornar a ship na posição dada")
    void testShipAt() {
        FakeShip s = new FakeShip("Fragata", 4, 4);
        FakeShip z = new FakeShip("Nau", 8, 7);
        fleet.addShip(s); fleet.addShip(z);

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