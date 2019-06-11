package ru.nerator.interpolation.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nerator.interpolation.model.point.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterpolationTest {

    private static List<Point> pl1,pl2,pl3;
    private Polynomial l;
    private final double EPSILON = 1E-6;

    @BeforeAll
    static void setUpBeforeClass() {
        pl1 = new ArrayList<>(Arrays.asList(
                new Point(-1.5, -14.1014),
                new Point(-0.75, -0.931596),
                new Point(0.0, 0.0),
                new Point(0.75, 0.931596),
                new Point(1.5, 14.1014))
        );
        pl2 = new ArrayList<>(Arrays.asList(
                new Point(0.0, 1.0),
                new Point(0.5, 0.0),
                new Point(1.0, -3.0),
                new Point(1.5, 4.0))
        );
        pl3 = new ArrayList<>(Arrays.asList(
                new Point(-3.0, -4.0),
                new Point(-1.0, -0.8),
                new Point(0.0, 1.6),
                new Point(1.0, 2.3),
                new Point(3.0, 1.5))
        );
    }

    @Test
    void testLagrange() {
        l = Interpolation.getLagrangePoly(pl1);

        assertEquals(      0.0, l.get(0), EPSILON);
        assertEquals(-1.477474, l.get(1), EPSILON);
        assertEquals(      0.0, l.get(2), EPSILON);
        assertEquals( 4.834848, l.get(3), EPSILON);

        l = Interpolation.getLagrangePoly(pl2);

        assertEquals(  1.0, l.get(0), EPSILON);
        assertEquals(  8.0, l.get(1), EPSILON);
        assertEquals(-28.0, l.get(2), EPSILON);
        assertEquals( 16.0, l.get(3), EPSILON);
    }

    @Test
    void testNewton() {
        l = Interpolation.getNewtonPoly(pl1);

        assertEquals(      0.0, l.get(0), EPSILON);
        assertEquals(-1.477474, l.get(1), EPSILON);
        assertEquals(      0.0, l.get(2), EPSILON);
        assertEquals( 4.834848, l.get(3), EPSILON);

        l = Interpolation.getNewtonPoly(pl2);

        assertEquals(  1.0, l.get(0), EPSILON);
        assertEquals(  8.0, l.get(1), EPSILON);
        assertEquals(-28.0, l.get(2), EPSILON);
        assertEquals( 16.0, l.get(3), EPSILON);

        assertThrows(IllegalArgumentException.class, () -> l = Interpolation.getNewtonPoly(pl3));
//		try {
//
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertEquals("X array must have same step.", e.getMessage());
//		}
    }

    @Test
    void testOLS1() {
        l = Interpolation.getOLSPoly1(pl1);

        assertEquals(0.0     , l.get(0), EPSILON);
        assertEquals(7.769172, l.get(1), EPSILON);

        l = Interpolation.getOLSPoly1(pl2);

        assertEquals(-0.4, l.get(0), EPSILON);
        assertEquals( 1.2, l.get(1), EPSILON);

        l = Interpolation.getOLSPoly1(pl3);

        assertEquals(0.12, l.get(0), EPSILON);
        assertEquals(0.98, l.get(1), EPSILON);
    }

    @AfterEach
    void tearDown() {
        l = null;
    }

    @AfterAll
    static void tearDownAfterClass() {
        pl1 = pl2 = pl3 = null;
    }
}
