package ru.nerator.interpolation.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nerator.interpolation.model.point.Point;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nerator
 *
 */
class ParseUtilTest {

    private static File file1, file2, file3;
    private static String[] args1, args2, args3;
    private static ArrayList<Point> pl1, pl2, pl3;

    private ArrayList<Point> pl;

    @BeforeAll
    static void setUpBeforeClass() {
        file1 = new File("src/test/resources/testpoints/func1");
        file2 = new File("src/test/resources/testpoints/func2");
        file3 = new File("src/test/resources/testpoints/func3");

        args1 = new String[]{
                "-1.5", "-14.1014",
                "-0.75", "-0.931596",
                "0.0", "0.0",
                "0.75", "0.931596",
                "1.5", "14.1014"
        };
        args2 = new String[]{
                "0.0", "1.0",
                "0.5", "0.0",
                "1.0", "-3.0",
                "1.5", "4.0"
        };
        args3 = new String[]{
                "-3.0", "-4.0",
                "-1.0", "-0.8",
                "0.0", "1.6",
                "1.0", "2.3",
                "3.0", "1.5"
        };

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

    /**
     * Test method for {@link ParseUtil#parseFile(File)}.
     * @throws IOException
     * @throws NumberFormatException
     */
    @Test
    void testParseFile() throws NumberFormatException, IOException {
        pl = ParseUtil.parseFile(file1);
        assertEquals(pl1,pl);

        pl = ParseUtil.parseFile(file2);
        assertEquals(pl2,pl);

        pl = ParseUtil.parseFile(file3);
        assertEquals(pl3,pl);
    }

    /**
     * Test method for {@link ParseUtil#parseArgs(String[])}.
     */
    @Test
    void testParseArgs() {
        pl = ParseUtil.parseArgs(args1);
        assertEquals(pl1, pl);

        pl = ParseUtil.parseArgs(args2);
        assertEquals(pl2, pl);

        pl = ParseUtil.parseArgs(args3);
        assertEquals(pl3, pl);
    }

    @AfterEach
    void tearDown() {
        pl = null;
    }

    @AfterAll
    static void tearDownAfterClass() {
        file1 = file2 = file3 = null;
        args1 = args2 = args3 = null;
        pl1 = pl2 = pl3 = null;
    }

}
