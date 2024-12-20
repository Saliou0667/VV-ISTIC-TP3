package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testEmptyString() {
        assertTrue(isBalanced(""));
    }

    @Test
    void testSingleOpening() {
        assertFalse(isBalanced("{"));
    }

    @Test
    void testSingleClosing() {
        assertFalse(isBalanced("]"));
    }

    @Test
    void testSimpleBalanced() {
        assertTrue(isBalanced("()"));
        assertTrue(isBalanced("[]"));
        assertTrue(isBalanced("{}"));
    }

    @Test
    void testMixedBalanced() {
        assertTrue(isBalanced("{[][]}({})"));
    }

    @Test
    void testMixedUnbalanced() {
        assertFalse(isBalanced("([)]"));
        assertFalse(isBalanced("{(}{})"));
    }

    @Test
    void testNullString() {
        assertFalse(isBalanced(null));
    }
}

