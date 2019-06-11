package ru.nerator.interpolation.model;

import java.util.ArrayList;

/**
 * Класс, реализующий тип данных "полином"
 * @author nerator
 */
public class Polynomial {

    private static final double EPSILON = 1E-6;

    private ArrayList<Double> coefs;

    /**
     * Константый полином 0
     */
    public static final Polynomial ZERO = new Polynomial(0.0);

    /**
     * Константый полином 1
     */
    public static final Polynomial ONE = new Polynomial(1.0);

    /**
     * Конструктор полинома на основе списка вещественных чисел
     * @param coefs список вещественных чисел
     */
    public Polynomial(ArrayList<Double> coefs) {
        this.coefs = coefs;
    }
    /**
     * Конструктор полинома на основе массива вещественных чисел
     * @param coefs массив вещественных чисел
     */
    public Polynomial(double[] coefs) {
        this.coefs = new ArrayList<>();
        for (double c : coefs) {
            this.coefs.add(c);
        }
    }

    /**
     * Конструктор полинома, представляющего число
     * @param c число
     */
    public Polynomial(double c) {
        this.coefs = new ArrayList<>();
        this.coefs.add(c);
    }

    /**
     * Возвращает коэффициент с заданным индексом
     * @param i индекс
     * @return коэффициент с индексом i
     */
    public double get(int i) {
        return this.coefs.get(i);
    }

    /**
     * Длина или порядок полинома - 1
     * @return количество коэффициентов в полиноме
     */
    public int length() {
        return this.coefs.size();
    }

    /**
     * Сложение полиномов
     * @param p полином
     * @return сумма полинома p и данного
     */
    public Polynomial add(Polynomial p) {
        int maxsize = Math.max(this.length(), p.length());
        ArrayList<Double> newCoefs = new ArrayList<>(maxsize);
        for (int i = 0; i < maxsize; i++) {
            if (i > this.length() - 1) {
                newCoefs.add(p.coefs.get(i));
            } else if (i > p.length() - 1) {
                newCoefs.add(this.coefs.get(i));
            } else {
                newCoefs.add(this.coefs.get(i) + p.coefs.get(i));
            }
        }
        return new Polynomial(newCoefs);
    }

    /**
     * Умножение полинома на число
     * @param num число
     * @return результат умножения данного полинома на число num
     */
    public Polynomial multByNum(double num) {
        ArrayList<Double> newCoefs = new ArrayList<>(this.length());
        for (Double coef : this.coefs) {
            newCoefs.add(coef * num);
        }
        return new Polynomial(newCoefs);
    }

    /**
     * Деление полинома на число
     * @param num число
     * @return результат деления данного полинома на число num
     */
    public Polynomial divideByNum(double num) {
        ArrayList<Double> newCoefs = new ArrayList<>(this.length());
        for (Double coef : this.coefs) {
            newCoefs.add(coef / num);
        }
        return new Polynomial(newCoefs);
    }

    /**
     * Умножение полинома на другой полином
     * @param p полином
     * @return результат умножения данного полинома на полином p
     */
    public Polynomial multByPoly(Polynomial p) {
        int newPolyLen = this.length() + p.length() - 1;
        ArrayList<Double> newCoefs = new ArrayList<>(newPolyLen);
        for (int i = 0; i < newPolyLen; i++) {
            newCoefs.add(0.0);
        }
        for (int i = 0; i < this.coefs.size(); i++) {
            for (int j = 0; j < p.coefs.size(); j++) {
                newCoefs.set(i + j, newCoefs.get(i + j) + this.get(i) * p.get(j));
            }
        }
        return new Polynomial(newCoefs);
    }

    /**
     * Значение полинома в точке
     * @param x координата
     * @return результат подстановки x в полином
     */
    public double getValue(double x) {
        double res = 0.0;

        for (int i = 0; i < this.coefs.size(); i++) {
            res += Math.pow(x, i)*this.get(i);
        }

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Polynomial) {
            Polynomial p = (Polynomial) o;
            if (this.length() != p.length()) {
                return this.allZeroes() && p.allZeroes();
            } else {
                for (int i=0; i<this.length(); i++) {
                    if (Math.abs(this.get(i)-p.get(i))>EPSILON) {
                        return false;
                    }
                }
                return true;
            }
        } else return false;
    }

    /**
     * Предикат, определяющий равны ли все коэффициенты в полиноме нулю
     * @return true, если все коэффициенты равны нулю (в пределах точности EPSILON)
     */
    private boolean allZeroes() {
        for (double coef : coefs) {
            if (Math.abs(coef)>EPSILON)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.equals(ZERO)) {
            return "0.0";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.length(); i++) {
            if (Math.abs(this.get(i)) > EPSILON) {
                //if (this.geti(i) != 0) {
                switch (i) {
                    case 0:
                        res.append(this.get(i));
                        if (this.length() != 1) {
                            res.append(" ");
                        }
                        break;
                    case 1:
                        if (res.toString().equals("")) {
                            res.append(this.get(i)).append("*x");
                        } else if (this.get(i) < 0) {
                            res.append("- ").append(Math.abs(this.get(i))).append("*x");
                        } else {
                            res.append("+ ").append(this.get(i)).append("*x");
                        }

                        if (this.length() != 2) {
                            res.append(" ");
                        }
                        break;
                    default:
                        if (res.toString().equals("")) {
                            res.append(this.get(i)).append("*x^").append(i);
                        } else if (this.get(i) < 0) {
                            res.append("- ").append(Math.abs(this.get(i))).append("*x^").append(i);
                        } else {
                            res.append("+ ").append(this.get(i)).append("*x^").append(i);
                        }

                        if (i != this.length() - 1) {
                            res.append(" ");
                        }
                }
            }
        }
        return res.toString();
    }



}
