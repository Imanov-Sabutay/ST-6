package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the TicTacToeCell class
 */
public class TicTacToeCellTest {
    
    private TicTacToeCell cell;

    @Before
    public void setUp() {
        cell = new TicTacToeCell(0, 0, 0);
    }

    @Test
    public void testCellInitialization() {
        assertNotNull(cell);
        assertEquals(0, cell.getNum());
        assertEquals(0, cell.getRow());
        assertEquals(0, cell.getCol());
        assertEquals(' ', cell.getMarker());
    }

    @Test
    public void testCellInitializationWithDifferentCoordinates() {
        TicTacToeCell cell1 = new TicTacToeCell(1, 1, 1);
        assertEquals(1, cell1.getNum());
        assertEquals(1, cell1.getRow());
        assertEquals(1, cell1.getCol());
    }

    @Test
    public void testCellInitializationAllPositions() {
        for (int num = 0; num < 9; num++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    TicTacToeCell testCell = new TicTacToeCell(num, x, y);
                    assertEquals(num, testCell.getNum());
                    assertEquals(y, testCell.getRow());
                    assertEquals(x, testCell.getCol());
                }
            }
        }
    }

    @Test
    public void testSetMarkerX() {
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

    @Test
    public void testSetMarkerO() {
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

    @Test
    public void testSetMarkerSpace() {
        cell.setMarker(" ");
        assertEquals(' ', cell.getMarker());
    }

    @Test
    public void testGetMarkerInitial() {
        assertEquals(' ', cell.getMarker());
    }

    @Test
    public void testGetRow() {
        assertEquals(0, cell.getRow());
        
        TicTacToeCell cell1 = new TicTacToeCell(0, 0, 1);
        assertEquals(1, cell1.getRow());
        
        TicTacToeCell cell2 = new TicTacToeCell(0, 0, 2);
        assertEquals(2, cell2.getRow());
    }

    @Test
    public void testGetCol() {
        assertEquals(0, cell.getCol());
        
        TicTacToeCell cell1 = new TicTacToeCell(0, 1, 0);
        assertEquals(1, cell1.getCol());
        
        TicTacToeCell cell2 = new TicTacToeCell(0, 2, 0);
        assertEquals(2, cell2.getCol());
    }

    @Test
    public void testGetNum() {
        TicTacToeCell cell0 = new TicTacToeCell(0, 0, 0);
        assertEquals(0, cell0.getNum());
        
        TicTacToeCell cell4 = new TicTacToeCell(4, 1, 1);
        assertEquals(4, cell4.getNum());
        
        TicTacToeCell cell8 = new TicTacToeCell(8, 2, 2);
        assertEquals(8, cell8.getNum());
    }

    @Test
    public void testCellDisabledAfterMarker() {
        assertTrue(cell.isEnabled());
        cell.setMarker("X");
        assertFalse(cell.isEnabled());
    }

    @Test
    public void testMultipleCells() {
        TicTacToeCell[] cells = new TicTacToeCell[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = new TicTacToeCell(i, i % 3, i / 3);
            assertEquals(i, cells[i].getNum());
        }
    }

    @Test
    public void testCellMarkerChange() {
        assertEquals(' ', cell.getMarker());
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        // Note: Cannot change marker back after disabled
    }

    @Test
    public void testCellGridLayout() {
        // Simulate 3x3 grid
        TicTacToeCell[][] grid = new TicTacToeCell[3][3];
        int num = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = new TicTacToeCell(num, col, row);
                assertEquals(num, grid[row][col].getNum());
                assertEquals(row, grid[row][col].getRow());
                assertEquals(col, grid[row][col].getCol());
                num++;
            }
        }
    }

    @Test
    public void testCellTextContent() {
        // Initial state has space
        assertEquals(' ', cell.getMarker());
        
        // After setting marker
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
    }
}
