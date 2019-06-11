package ru.nerator.interpolation.util;

/**
 * Вспомогательный класс, реализующий факториал
 * @author nerator
 *
 */
public class FactorialUtil {

    private FactorialUtil() {}

    /**
     * Факториал числа x
     * @param x число
     * @return значение x!
     */
    public static long factorial(int x) {
        long res = 1;
        for (int i = 1; i <= x; i++)
            res *= i;
        return res;
    }

}
