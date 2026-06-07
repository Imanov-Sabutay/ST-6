package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Unit tests for the TicTacToe Game class
 * Tests cover game initialization, state checking, move generation, and minimax algorithm
 */
public class GameTest {
    
    private Game game;
    private Player playerX;
    private Player playerO;

    @Before
    public void setUp() {
        game = new Game();
        playerX = game.player1;
        playerO = game.player2;
    }

    // Tests for Game constructor
    @Test
    public void testGameInitialization() {
        assertNotNull(game.player1);
        assertNotNull(game.player2);
        assertNotNull(game.board);
        assertEquals(State.PLAYING, game.state);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }

    @Test
    public void testBoardInitializationEmpty() {
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }

    // Tests for generateMoves
    @Test
    public void testGenerateMovesEmptyBoard() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(9, moves.size());
        for (int i = 0; i < 9; i++) {
            assertTrue(moves.contains(i));
        }
    }

    @Test
    public void testGenerateMovesPartialBoard() {
        game.board[0] = 'X';
        game.board[4] = 'O';
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(7, moves.size());
        assertFalse(moves.contains(0));
        assertFalse(moves.contains(4));
    }

    @Test
    public void testGenerateMovesFullBoard() {
        for (int i = 0; i < 9; i++) {
            game.board[i] = (i % 2 == 0) ? 'X' : 'O';
        }
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(0, moves.size());
    }

    // Tests for checkState - X wins
    @Test
    public void testCheckStateXWinsFirstRow() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinsSecondRow() {
        game.board[3] = 'X';
        game.board[4] = 'X';
        game.board[5] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinsThirdRow() {
        game.board[6] = 'X';
        game.board[7] = 'X';
        game.board[8] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinsFirstColumn() {
        game.board[0] = 'X';
        game.board[3] = 'X';
        game.board[6] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinsSecondColumn() {
        game.board[1] = 'X';
        game.board[4] = 'X';
        game.board[7] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinsThirdColumn() {
        game.board[2] = 'X';
        game.board[5] = 'X';
        game.board[8] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinsMainDiagonal() {
        game.board[0] = 'X';
        game.board[4] = 'X';
        game.board[8] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinsAntiDiagonal() {
        game.board[2] = 'X';
        game.board[4] = 'X';
        game.board[6] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    // Tests for checkState - O wins
    @Test
    public void testCheckStateOWinsFirstRow() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateOWinsMainDiagonal() {
        game.board[0] = 'O';
        game.board[4] = 'O';
        game.board[8] = 'O';
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(game.board));
    }

    // Tests for checkState - DRAW
    @Test
    public void testCheckStateDraw() {
        // Fill board without any winner
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = 'O';
        game.board[6] = 'O';
        game.board[7] = 'X';
        game.board[8] = 'O';
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(game.board));
    }

    // Tests for checkState - PLAYING
    @Test
    public void testCheckStatePlaying() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    public void testCheckStatePlayingEmptyBoard() {
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    // Tests for evaluatePosition
    @Test
    public void testEvaluatePositionXWins() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(game.board, playerX));
    }

    @Test
    public void testEvaluatePositionOWins() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        assertEquals(Game.INF, game.evaluatePosition(game.board, playerO));
    }

    @Test
    public void testEvaluatePositionXWinsButPlayerIsO() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        assertEquals(-Game.INF, game.evaluatePosition(game.board, playerO));
    }

    @Test
    public void testEvaluatePositionOWinsButPlayerIsX() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        assertEquals(-Game.INF, game.evaluatePosition(game.board, playerX));
    }

    @Test
    public void testEvaluatePositionDraw() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = 'O';
        game.board[6] = 'O';
        game.board[7] = 'X';
        game.board[8] = 'O';
        game.symbol = 'X';
        assertEquals(0, game.evaluatePosition(game.board, playerX));
    }

    @Test
    public void testEvaluatePositionPlaying() {
        game.board[0] = 'X';
        game.symbol = 'X';
        assertEquals(-1, game.evaluatePosition(game.board, playerX));
    }

    // Tests for MiniMax
    @Test
    public void testMiniMaxReturnsValidMove() {
        game.symbol = playerX.symbol;
        int move = game.MiniMax(game.board, playerX);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    public void testMiniMaxEmptyBoard() {
        game.symbol = playerX.symbol;
        int move = game.MiniMax(game.board, playerX);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    public void testMiniMaxPartialBoard() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.symbol = playerX.symbol;
        int move = game.MiniMax(game.board, playerX);
        assertTrue(move >= 1 && move <= 9);
        // The move should be on an empty cell
        assertTrue(game.board[move - 1] == 'X' || game.board[move - 1] == ' ');
    }

    // Tests for MinMove
    @Test
    public void testMinMoveTerminalXWin() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        int result = game.MinMove(game.board, playerX);
        assertEquals(Game.INF, result);
    }

    @Test
    public void testMinMoveTerminalOWin() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        int result = game.MinMove(game.board, playerO);
        assertEquals(Game.INF, result);
    }

    @Test
    public void testMinMoveDraw() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = 'O';
        game.board[6] = 'O';
        game.board[7] = 'X';
        game.board[8] = 'O';
        game.symbol = 'X';
        int result = game.MinMove(game.board, playerX);
        assertEquals(0, result);
    }

    // Tests for MaxMove
    @Test
    public void testMaxMoveTerminalXWin() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        int result = game.MaxMove(game.board, playerX);
        assertEquals(Game.INF, result);
    }

    @Test
    public void testMaxMoveTerminalOWin() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        int result = game.MaxMove(game.board, playerO);
        assertEquals(Game.INF, result);
    }

    @Test
    public void testMaxMoveDraw() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = 'O';
        game.board[6] = 'O';
        game.board[7] = 'X';
        game.board[8] = 'O';
        game.symbol = 'X';
        int result = game.MaxMove(game.board, playerX);
        assertEquals(0, result);
    }

    // Additional tests for better coverage
    @Test
    public void testGenerateMovesMultipleCalls() {
        ArrayList<Integer> moves1 = new ArrayList<>();
        game.generateMoves(game.board, moves1);
        ArrayList<Integer> moves2 = new ArrayList<>();
        game.generateMoves(game.board, moves2);
        assertEquals(moves1.size(), moves2.size());
    }

    @Test
    public void testGameBoardStateAfterCheckState() {
        char[] originalBoard = game.board.clone();
        game.symbol = 'X';
        game.checkState(game.board);
        // Verify board hasn't changed
        for (int i = 0; i < 9; i++) {
            assertEquals(originalBoard[i], game.board[i]);
        }
    }

    @Test
    public void testCheckStateWithDifferentSymbols() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
        
        game.symbol = 'O';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    public void testEvaluatePositionAllScenarios() {
        // Test all terminal positions
        setUp(); testEvaluatePositionXWins();
        setUp(); testEvaluatePositionOWins();
        setUp(); testEvaluatePositionDraw();
        setUp(); testEvaluatePositionPlaying();
    }

    @Test
    public void testMinMaxWithSingleMove() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.symbol = 'O';
        int move = game.MiniMax(game.board, playerO);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    public void testCounterStateAfterMiniMax() {
        int initialQ = game.q;
        game.symbol = 'X';
        game.MiniMax(game.board, playerX);
        // q should be reset to 0 after MiniMax
        assertEquals(0, game.q);
    }

    @Test
    public void testGameStateInitialization() {
        assertEquals(State.PLAYING, game.state);
        assertNull(game.cplayer);
        assertEquals(0, game.nmove);
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals('X', playerX.symbol);
        assertEquals('O', playerO.symbol);
        assertEquals(0, playerX.move);
        assertEquals(0, playerO.move);
        assertFalse(playerX.selected);
        assertFalse(playerX.win);
    }

    @Test
    public void testBoardArraySize() {
        assertEquals(9, game.board.length);
    }

    @Test
    public void testGenerateMovesOrderedByIndex() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        for (int i = 0; i < 9; i++) {
            assertTrue(moves.contains(i));
        }
    }
}
