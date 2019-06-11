package ru.nerator.interpolation.model;

import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import ru.nerator.interpolation.model.point.Point;
import ru.nerator.interpolation.util.FactorialUtil;

public class Interpolation {

    private static double EPSILON = 1E-6;

    private Interpolation() {}

    /**
     * Возвращает полином Лагранжа для заданного списка точек
     * @param pl список точек
     * @return полином Лагранжа
     */
    public static Polynomial getLagrangePoly(List<Point> pl) {
        Polynomial res = Polynomial.ZERO;
        for (int i = 0; i < pl.size(); i++) {
            res = res.add(getBasisPoly(Point.getXArray(pl),i).multByNum(pl.get(i).getY()));
        }
        return res;
    }

    /**
     * Возвращает базисный полином для списка координат X и номера координаты
     * @param x массив координат X (узлов интерполяции)
     * @param i индекс координаты, для которой ищется базисный полином
     * @return базисный полином
     */
    private static Polynomial getBasisPoly(double[] x, int i) {
        Polynomial res = Polynomial.ONE;
        for (int j = 0; j < x.length; j++) {
            if (i != j) {
                Polynomial p = new Polynomial(new double[] { -x[j], 1 });
                res = res.multByPoly(p.divideByNum(x[i] - x[j]));
            }
        }
        return res;
    }

    /**
     * Возвращает полином Ньютона для заданного списка точек
     * @param pl список точек
     * @return полином Ньютона
     */
    public static Polynomial getNewtonPoly(List<Point> pl) {
        Double h = getStep(Point.getXArray(pl));

        Polynomial res = Polynomial.ZERO;
        for (int i=0; i<pl.size(); i++) {
            double coefficient = finiteDifference(Point.getYArray(pl), i, 0) /
                    (FactorialUtil.factorial(i)*Math.pow(h, i));
            Polynomial p = Polynomial.ONE;
            for (int j=0; j<i; j++) {
                p = p.multByPoly(new Polynomial(new double[]{-pl.get(j).getX(), 1}));
            }
            res = res.add(p.multByNum(coefficient));
        }
        return res;
    }

    /**
     * Возвращает значение шага для массива координат X
     * @param x массив координат X (узлов интерполяции)
     * @return значение шага
     * @throws IllegalArgumentException если невозможно определить шаг
     */
    private static Double getStep(double[] x) {
        double step = x[1]-x[0];
        for (int i=1; i<x.length-1; i++) {
            if (Math.abs(x[i+1]-x[i]-step)>EPSILON) {
                throw new IllegalArgumentException("X array must have same step.");
            }
        }
        return step;
    }

    /**
     * Возвращает значение конечной разницы для массива Y заданного порядка для
     * заданной точки
     * @param y массив координат Y
     * @param order порядок разности
     * @param index индекс точки, для которой находим разность
     * @return значение конечной разницы
     * @throws IllegalArgumentException если index больше чем длина массива - 1
     */
    private static Double finiteDifference(double[] y, int order, int index) {
        if (index > y.length-1) {
            throw new IllegalArgumentException("Index must not be bigger, than length of y - 1.");
        }
        switch (order) {
            case 0:
                return y[index];
            case 1:
                return y[index+1]-y[index];
            default:
                return finiteDifference(y, order-1, index+1)-finiteDifference(y, order-1, index);
        }
    }

    /**
     * Нахождение полинома 1 порядка методом наименьших квадратов
     * с использованием лямбда-выражений.
     * @param pl список точек
     * @return интерполяционный полином 1 порядка
     */
    public static Polynomial getOLSPoly1(List<Point> pl) {
        int n = pl.size();
        double[] xs = Point.getXArray(pl);
        double[] ys = Point.getYArray(pl);
        double sumx = Arrays.stream(xs).sum();
        double sumy = Arrays.stream(ys).sum();
        double sumx2 = Arrays.stream(xs).map((x) -> Math.pow(x, 2)).sum();
        double sumxy = Arrays.stream(simpleZipWith(((x,y) -> x*y), xs, ys)).sum();

        double a = (n*sumxy - sumx*sumy)/(n*sumx2 - Math.pow(sumx, 2.0));
        double b = (sumy - a*sumx)/n;

        return new Polynomial(new double[]{b,a});
    }


    /**
     * Простая реализация функционала zipWith
     * @param binfunc функция, используемая для склейки
     * @param arr1 массив 1
     * @param arr2 массив 2
     * @return массив, получаемый применением binfunc к соответствующим
     *         элементам массивов
     */
    private static double[] simpleZipWith(DoubleBinaryOperator binfunc, double[] arr1, double[] arr2) {
        int resSize = arr1.length < arr2.length ? arr1.length : arr2.length;
        double[] res = new double[resSize];

        for (int i=0; i<resSize; i++) {
            res[i] = binfunc.applyAsDouble(arr1[i], arr2[i]);
        }

        return res;
    }



}
