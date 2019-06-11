package ru.nerator.interpolation.model.point;

import java.util.List;

/**
 * Класс, реализующий точку - по сути кортеж двух вещественных чисел
 *
 * @author nerator
 */
public class Point {

    private double x, y;

    /**
     * Конструктор по умолчанию - точка (0.0, 0.0)
     */
    public Point() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Конструктор точки с координатами (px, py)
     *
     * @param px координата x
     * @param py координата y
     */
    public Point(double px, double py) {
        this.x = px;
        this.y = py;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point p = (Point) obj;
            double EPSILON = 1E-6;
            return Math.abs(this.getX() - p.getX()) < EPSILON &&
                    Math.abs(this.getY() - p.getY()) < EPSILON;
        } else
            return false;
    }

    /**
     * Возвращает массив координат X заданного списка точек
     *
     * @param pl список точек
     * @return массив координат X
     */
    public static double[] getXArray(List<Point> pl) {
        double[] res = new double[pl.size()];
        for (int i = 0; i < pl.size(); i++) {
            res[i] = pl.get(i).getX();
        }
        return res;
    }

    /**
     * Возвращает массив координат Y заданного списка точек
     *
     * @param pl список точек
     * @return массив координат Y
     */
    public static double[] getYArray(List<Point> pl) {
        double[] res = new double[pl.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = pl.get(i).getY();
        }
        return res;
    }

}
