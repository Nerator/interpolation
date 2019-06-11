package ru.nerator.interpolation.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import ru.nerator.interpolation.model.point.Point;

/**
 * Вспомогательный класс для разбора файла с исходными данными или
 * аргументов командной строки
 * @author nerator
 */
public class ParseUtil {

    private ParseUtil() {}

    /**
     * Считывание входных данных из файла
     * @param in файл входных данных
     * @return список точек, считанных из файла
     * @throws NumberFormatException в случае ошибки формата
     * @throws IOException в случае ошибки ввода/вывода
     */
    public static ArrayList<Point> parseFile(File in) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(in));
        ArrayList<Point> res = new ArrayList<>();
        String str;
        while ((str = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(str);
            res.add(new Point(Double.parseDouble(st.nextToken()),
                    Double.parseDouble(st.nextToken())));
        }
        br.close();
        return res;
    }

    /**
     * Считывание входных данных из аргументов командной строки
     * @param args аргументы командной строки
     * @return список точек, считанных из аргументов командной строки
     * @throws NumberFormatException в случае ошибки формата
     */
    public static ArrayList<Point> parseArgs(String[] args) {
        ArrayList<Point> res = new ArrayList<>();
        for (int i = 0; 2 * i < args.length; i++) {
            res.add(new Point(Double.parseDouble(args[2 * i]),
                    Double.parseDouble(args[2 * i + 1])));
        }
        return res;
    }

}
