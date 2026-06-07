package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the State enum
 */
public class StateTest {
    
    @Test
    public void testStateEnumValues() {
        State[] states = State.values();
        assertEquals(4, states.length);
    }

    @Test
    public void testStatePlayingExists() {
        assertTrue(enumContains("PLAYING"));
    }

    @Test
    public void testStateOWinExists() {
        assertTrue(enumContains("OWIN"));
    }

    @Test
    public void testStateXWinExists() {
        assertTrue(enumContains("XWIN"));
    }

    @Test
    public void testStateDrawExists() {
        assertTrue(enumContains("DRAW"));
    }

    @Test
    public void testStateValueOf() {
        assertEquals(State.PLAYING, State.valueOf("PLAYING"));
        assertEquals(State.XWIN, State.valueOf("XWIN"));
        assertEquals(State.OWIN, State.valueOf("OWIN"));
        assertEquals(State.DRAW, State.valueOf("DRAW"));
    }

    @Test
    public void testStateComparison() {
        State state1 = State.XWIN;
        State state2 = State.XWIN;
        assertEquals(state1, state2);
        
        State state3 = State.OWIN;
        assertNotEquals(state1, state3);
    }

    @Test
    public void testAllStatesUnique() {
        State[] states = State.values();
        for (int i = 0; i < states.length; i++) {
            for (int j = i + 1; j < states.length; j++) {
                assertNotEquals(states[i], states[j]);
            }
        }
    }

    @Test
    public void testStateOrdinal() {
        // Test that states have different ordinals
        assertNotEquals(State.PLAYING.ordinal(), State.XWIN.ordinal());
        assertNotEquals(State.XWIN.ordinal(), State.OWIN.ordinal());
        assertNotEquals(State.OWIN.ordinal(), State.DRAW.ordinal());
    }

    @Test
    public void testStateToString() {
        assertNotNull(State.PLAYING.toString());
        assertNotNull(State.XWIN.toString());
        assertNotNull(State.OWIN.toString());
        assertNotNull(State.DRAW.toString());
    }

    @Test
    public void testStateEquality() {
        State playing1 = State.PLAYING;
        State playing2 = State.PLAYING;
        assertTrue(playing1 == playing2);
    }

    private boolean enumContains(String value) {
        try {
            State.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
