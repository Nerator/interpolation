package ru.nerator.interpolation.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nerator
 *
 */
class FactorialUtilTest {

    /**
     * Test method for {@link FactorialUtil#factorial(int)}.
     */
    @Test
    void testTestFactorial() {
        assertEquals(      1, FactorialUtil.factorial( 0));
        assertEquals(      1, FactorialUtil.factorial( 1));
        assertEquals(      2, FactorialUtil.factorial( 2));
        assertEquals(      6, FactorialUtil.factorial( 3));
        assertEquals(     24, FactorialUtil.factorial( 4));
        assertEquals(    120, FactorialUtil.factorial( 5));
        assertEquals(    720, FactorialUtil.factorial( 6));
        assertEquals(   5040, FactorialUtil.factorial( 7));
        assertEquals(  40320, FactorialUtil.factorial( 8));
        assertEquals( 362880, FactorialUtil.factorial( 9));
        assertEquals(3628800, FactorialUtil.factorial(10));

    }

}
