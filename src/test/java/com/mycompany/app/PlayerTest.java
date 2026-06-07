package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Player class
 */
public class PlayerTest {
    
    private Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testPlayerInitialization() {
        assertNotNull(player);
    }

    @Test
    public void testPlayerSymbolAssignment() {
        player.symbol = 'X';
        assertEquals('X', player.symbol);
        
        player.symbol = 'O';
        assertEquals('O', player.symbol);
    }

    @Test
    public void testPlayerMoveAssignment() {
        player.move = 5;
        assertEquals(5, player.move);
        
        player.move = 0;
        assertEquals(0, player.move);
        
        player.move = -1;
        assertEquals(-1, player.move);
    }

    @Test
    public void testPlayerSelectedFlag() {
        assertFalse(player.selected);
        
        player.selected = true;
        assertTrue(player.selected);
        
        player.selected = false;
        assertFalse(player.selected);
    }

    @Test
    public void testPlayerWinFlag() {
        assertFalse(player.win);
        
        player.win = true;
        assertTrue(player.win);
        
        player.win = false;
        assertFalse(player.win);
    }

    @Test
    public void testPlayerMultipleAttributes() {
        player.symbol = 'X';
        player.move = 3;
        player.selected = true;
        player.win = false;
        
        assertEquals('X', player.symbol);
        assertEquals(3, player.move);
        assertTrue(player.selected);
        assertFalse(player.win);
    }

    @Test
    public void testPlayerAttributeIndependence() {
        Player player1 = new Player();
        Player player2 = new Player();
        
        player1.symbol = 'X';
        player2.symbol = 'O';
        
        assertEquals('X', player1.symbol);
        assertEquals('O', player2.symbol);
        assertNotEquals(player1.symbol, player2.symbol);
    }

    @Test
    public void testPlayerMoveRange() {
        player.move = 1;
        assertEquals(1, player.move);
        
        player.move = 9;
        assertEquals(9, player.move);
        
        player.move = 0;
        assertEquals(0, player.move);
    }
}
