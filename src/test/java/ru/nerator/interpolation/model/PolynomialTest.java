package ru.nerator.interpolation.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    private static Polynomial p1,p2,zeroes;
    private final double EPSILON = 1E-6;

    @BeforeAll
    static void setUpBeforeClass() {
        p1 = new Polynomial(new double[] {1.0,2.0});
        p2 = new Polynomial(new double[] {3.0,4.0});
        zeroes = new Polynomial(new double[]{0.0,0.0,0.0});
    }

    @Test
    void testAdd() {
        Polynomial p3 = p1.add(p2);
        assertEquals(4.0, p3.get(0), EPSILON);
        assertEquals(6.0, p3.get(1), EPSILON);

        Polynomial p4 = p1.add(Polynomial.ZERO);
        assertEquals(1.0, p4.get(0), EPSILON);
        assertEquals(2.0, p4.get(1), EPSILON);
    }

    @Test
    void testMultNum() {
        Polynomial p3 = p1.multByNum(5.0);
        assertEquals( 5.0, p3.get(0), EPSILON);
        assertEquals(10.0, p3.get(1), EPSILON);

        Polynomial p4 = p2.multByNum(10.0);
        assertEquals(30.0, p4.get(0), EPSILON);
        assertEquals(40.0, p4.get(1), EPSILON);
    }

    @Test
    void testDivideNum() {
        Polynomial p3 = p1.divideByNum(0.5);
        assertEquals(2.0, p3.get(0), EPSILON);
        assertEquals(4.0, p3.get(1), EPSILON);

        Polynomial p4 = p2.divideByNum(2.0);
        assertEquals(1.5, p4.get(0), EPSILON);
        assertEquals(2.0, p4.get(1), EPSILON);
    }

    @Test
    void testMultPoly() {
        Polynomial p3 = p1.multByPoly(p2);
        Polynomial p4 = p2.multByPoly(Polynomial.ONE);
        Polynomial p5 = p1.multByPoly(Polynomial.ZERO);

        assertEquals( 3.0, p3.get(0), EPSILON);
        assertEquals(10.0, p3.get(1), EPSILON);
        assertEquals( 8.0, p3.get(2), EPSILON);

        assertEquals(3.0, p4.get(0), EPSILON);
        assertEquals(4.0, p4.get(1), EPSILON);

        assertEquals(0.0, p5.get(0), EPSILON);
    }

    @Test
    void testEquals() {
        Polynomial p3 = new Polynomial(new double[] {1.0,2.0});
        Polynomial p4 = new Polynomial(new double[] {3.0,4.0});

        assertEquals(p1, p1);
        assertNotEquals(p1, p2);
        assertEquals(p1, p3);
        assertNotEquals(p1, p4);

        assertNotEquals(p2, p1);
        assertEquals(p2, p2);
        assertNotEquals(p2, p3);
        assertEquals(p2, p4);

        assertEquals(p3, p1);
        assertNotEquals(p3, p2);
        assertEquals(p3, p3);
        assertNotEquals(p3, p4);

        assertNotEquals(p4, p1);
        assertEquals(p4, p2);
        assertNotEquals(p4, p3);
        assertEquals(p4, p4);

        assertEquals(zeroes, Polynomial.ZERO);

        assertNotEquals(p1, new Object());
    }

    @Test
    void testToString() {
        assertEquals("0.0", Polynomial.ZERO.toString());
        assertEquals("0.0", zeroes.toString());
        assertEquals("1.0", Polynomial.ONE.toString());
        assertEquals("1.0 + 2.0*x", p1.toString());
        assertEquals("3.0 + 4.0*x", p2.toString());
    }

    @AfterAll
    static void tearDownAfterClass() {
        p1 = p2 = zeroes = null;
    }

}
