package com.mycompany.app;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Unit tests for the Utility class
 */
public class UtilityTest {
    
    @Test
    public void testUtilityExists() {
        assertNotNull(new Utility());
    }

    @Test
    public void testPrintCharArray() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        // This should not throw an exception
        Utility.print(board);
    }

    @Test
    public void testPrintCharArrayWithValues() {
        char[] board = new char[9];
        board[0] = 'X';
        board[1] = 'O';
        board[2] = 'X';
        for (int i = 3; i < 9; i++) {
            board[i] = ' ';
        }
        // This should not throw an exception
        Utility.print(board);
    }

    @Test
    public void testPrintIntArray() {
        int[] array = new int[9];
        for (int i = 0; i < 9; i++) {
            array[i] = i + 1;
        }
        // This should not throw an exception
        Utility.print(array);
    }

    @Test
    public void testPrintIntArrayZeros() {
        int[] array = new int[9];
        for (int i = 0; i < 9; i++) {
            array[i] = 0;
        }
        // This should not throw an exception
        Utility.print(array);
    }

    @Test
    public void testPrintArrayListEmpty() {
        ArrayList<Integer> moves = new ArrayList<>();
        // This should not throw an exception
        Utility.print(moves);
    }

    @Test
    public void testPrintArrayListWithValues() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(1);
        moves.add(2);
        moves.add(3);
        moves.add(4);
        moves.add(5);
        // This should not throw an exception
        Utility.print(moves);
    }

    @Test
    public void testPrintArrayListSingleValue() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(5);
        // This should not throw an exception
        Utility.print(moves);
    }

    @Test
    public void testPrintCharArrayAllSymbols() {
        char[] board = new char[] { 'X', 'O', 'X', 'O', 'X', ' ', ' ', 'O', ' ' };
        // This should not throw an exception
        Utility.print(board);
    }

    @Test
    public void testPrintIntArrayWithNegatives() {
        int[] array = new int[] { -1, -2, -3, 0, 1, 2, 3, 4, 5 };
        // This should not throw an exception
        Utility.print(array);
    }

    @Test
    public void testPrintArrayListMultipleValues() {
        ArrayList<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            moves.add(i);
        }
        // This should not throw an exception
        Utility.print(moves);
    }
}
