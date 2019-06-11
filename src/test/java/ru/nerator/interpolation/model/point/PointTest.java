package ru.nerator.interpolation.model.point;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    private static List<Point> pl1, pl2;
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
    }

    /**
     * Test method for:
     * {@link Point#Point()},
     * {@link Point#Point(double, double)},
     * {@link Point#getX()},
     * {@link Point#getY()}.
     */
    @Test
    void testConstructorAndAccessors() {
        Point p1 = new Point();
        assertEquals(0.0, p1.getX(), EPSILON);
        assertEquals(0.0, p1.getY(), EPSILON);
        Point p2 = new Point(1.0, 2.0);
        assertEquals(1.0, p2.getX(), EPSILON);
        assertEquals(2.0, p2.getY(), EPSILON);
    }

    /**
     * Test method for {@link Point#equals(Object)}.
     */
    @Test
    void testEquals() {
        Point p1 = new Point();
        Point p2 = new Point(1.0, 2.0);
        Point p3 = new Point(1.0, 2.0);
        assertNotEquals(p1, p2);
        assertEquals(p2, p3);
    }

    /**
     * Test method for {@link Point#getXArray(List)}.
     */
    @Test
    void testGetXArray() {
        double[] exppl1 = new double[]{-1.5, -0.75, 0.0, 0.75, 1.5};
        assertArrayEquals(exppl1, Point.getXArray(pl1), EPSILON);
        double[] exppl2 = new double[]{0.0, 0.5, 1.0, 1.5};
        assertArrayEquals(exppl2, Point.getXArray(pl2), EPSILON);
    }

    /**
     * Test method for {@link Point#getYArray(List)}.
     */
    @Test
    void testGetYArray() {
        double[] exppl1 = new double[]{-14.1014, -0.931596, 0.0, 0.931596, 14.1014};
        assertArrayEquals(exppl1, Point.getYArray(pl1), EPSILON);
        double[] exppl2 = new double[]{1.0, 0.0, -3.0, 4.0};
        assertArrayEquals(exppl2, Point.getYArray(pl2), EPSILON);
    }

    @AfterAll
    static void tearDownAfterClass() {
        pl1 = pl2 = null;
    }

}
