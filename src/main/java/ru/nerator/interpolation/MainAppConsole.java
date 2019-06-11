package ru.nerator.interpolation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import ru.nerator.interpolation.model.Interpolation;
import ru.nerator.interpolation.model.Polynomial;
import ru.nerator.interpolation.model.point.Point;
import ru.nerator.interpolation.util.ParseUtil;

public class MainAppConsole {

    private enum Mode {
        LAGRANGE, NEWTON, OLS1, OLS2, ALL;

        private static Mode getMode(String string) {
            switch (string) {
                case "lagrange":
                    return Mode.LAGRANGE;
                case "newton":
                    return Mode.NEWTON;
                case "ols1":
                    return Mode.OLS1;
                case "ols2":
                    return Mode.OLS2;
                case "all":
                    return Mode.ALL;
                default:
                    invalidArgs("Invalid mode");
                    return null;
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("--help") || args[0].equals("-h")) {
            showUsage();
            System.exit(0);
        } else if (args[0].equals("--file") || args[0].equals("-f")) {
            Mode mode = Mode.getMode(args[1]);
            File in = new File(args[2]);

            ArrayList<Point> points = null;
            try {
                points = ParseUtil.parseFile(in);
            } catch (FileNotFoundException e) {
                invalidArgs("File not found");
            } catch (IOException e) {
                System.out.println("IOException while reading file.");
                System.out.println(e.getMessage());
                System.exit(0);
            } catch (NumberFormatException e) {
                invalidArgs("Wrong number format: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Something wrong in a file.");
                System.exit(0);
            }

            printResult(mode, points);
            System.exit(0);
        } else if (args[0].equals("--points") || args[0].equals("-p")) {
            Mode mode = Mode.getMode(args[1]);
            ArrayList<Point> points = null;
            try {
                points = ParseUtil.parseArgs(
                        Arrays.copyOfRange(args, 2, args.length));
            } catch (NumberFormatException e) {
                invalidArgs("Wrong number format: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                invalidArgs();
            }
            printResult(mode, points);
            System.exit(0);

        } else {
            invalidArgs("Invalid command");
        }
    }

    private static void printResult(Mode mode, ArrayList<Point> points) {
        if (mode == Mode.LAGRANGE || mode == Mode.ALL) {
            System.out.println("Lagrange polynomial:");
            Polynomial l = Interpolation.getLagrangePoly(points);
            System.out.println(l);
            System.out.println();
        }
        if (mode == Mode.NEWTON || mode == Mode.ALL) {
            System.out.println("Newton polynomial:");
            Polynomial l;
            try {
                l = Interpolation.getNewtonPoly(points);
                System.out.println(l);
            } catch (IllegalArgumentException e) {
                System.out.println("Error in points: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
        if (mode == Mode.OLS1 || mode == Mode.ALL) {
            System.out.println("First degree Ordinary Least Squares Polynomial:");
            System.out.println(Interpolation.getOLSPoly1(points));
            System.out.println();
        }
        if (mode == Mode.OLS2 || mode == Mode.ALL) {
            System.out.println("Second degree Ordinary Least Squares Polynomial:");
            System.out.println("NYI"); // TODO: implement OLS2
            System.out.println();
        }
    }

    private static void invalidArgs() {
        invalidArgs("Invalid arguments.");
    }

    private static void invalidArgs(String message) {
        System.out.println("ERROR: " + message);
        showUsage();
        System.exit(0);
    }

    private static void showUsage() {
        System.out.println("Usage:\n");
        System.out.println("java -jar interpolation.jar --file mode filename");
        System.out.println("java -jar interpolation.jar -f mode filename");
        System.out.println("Use input from filename.\n");
        System.out.println("java -jar interpolation.jar --points mode x0 y0 x1 y1 ... xn yn");
        System.out.println("java -jar interpolation.jar -p mode x0 y0 x1 y1 ... xn yn");
        System.out.println("Use list of coodinates.\n");
        System.out.println("mode can be: lagrange, newton, ols1, ols2(nyi) all.");
        System.out.println();
        System.out.println("java -jar interpolation.jar --help");
        System.out.println("java -jar interpolation.jar -h");
        System.out.println("Show this message.\n");
    }

}
