package com.movavi.android.geophysics.core;


import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.List;

/** Класс для работы с математическими данными - подсчет корреляции и т.п. */
public class MyMath {

    // округлятель
    private static DecimalFormat df =new DecimalFormat("#.###");

    /*
     * Метод, возвращающий сумму коллекции *
     * @param List<Double> list - коллекция.
     * @return m_sum- сумма элементоа коллекции.
     */
    private static Double getSum(@NonNull final List<Double> list) {
        Double m_sum = 0.0;
        for (Double f : list) {
            m_sum += f;
        }
        return m_sum;
    }

    /*
     * Метод, возвращающий сумму элементов в коллекции, возведенных в квадрат  *
     * @param List<Double> list - коллекция.
     * @return m_sum - сумма элементоа коллекции.
     */
    /*сумма элементов в коллекции, возведенных в квадрат */
    private static Double getPowSum(@NonNull final List<Double> list) {
        double m_sum = 0.0;
        for (Double elem : list) {
            m_sum += Math.pow(elem, 2.0);
        }
        return m_sum;
    }

    /**
     * Метод, возвращающий сумму элементов двух коллекций, предворительно умноженных друг на друга  *
     * @param listY<Double> list - коллекция.
     * @return m_sum - сумма элементоа коллекции.
     */
    private static Double getMultipleSum(@NonNull final List<Double> listX, @NonNull final List<Double> listY) {
        double sum = 0.0;
        int i = 0;
        for (Double elem : listX) {
            sum += listX.get(i) * listY.get(i);
            i++;
        }
        return sum;
    }

    /**
     * Метод, возвращающий средне-арифметическое по столбцу=математическое ожидание *
     * @param list<Double> list - коллекция.
     * @return средне-аримитическое
     */
        private static Double getAvg(List<Double> list) {
        return getSum(list) / list.size();
    }


    /**
     * Метод, возвращающий коэффициент корреляции
     * @param listX - коллекция, содержащая числовой ряд для Х, @NonNull final List<Double> listY - для Y соответственно .
     * @return correlation  коэффициент корреляции
     */
    public static Double getCorellation(@NonNull final List<Double> listX, @NonNull final List<Double> listY) {
        /*вычисляем мат. ожидание для x и y*/
        double m_avgX = getAvg(listX);
        double m_avgY = getAvg(listY);
        /*коварация и сигмы*/
        double m_covariance = 0.0;
        double m_sigmaX = 0.0;
        double m_sigmaY = 0.0;
        /*кореляция*/
        double correlation;

        int i = 0;

        for (Double l : listX) {
            /*вычисляем коварацию для числового ряда*/
            m_covariance += (listX.get(i) - m_avgX) * (listY.get(i) - m_avgY);
            /*вычисляем сигмы (x-мат.ожидание(x))^2*/
            m_sigmaX += Math.pow(listX.get(i) - m_avgX, 2.0);
            m_sigmaY += Math.pow(listY.get(i) - m_avgY, 2.0);
            i++;
        }
        /* кореляция = коварация/корень квадратный от произведения сигм*/
        correlation = m_covariance / Math.sqrt(m_sigmaX * m_sigmaY);
        return correlation;
    }

    /**
     * Метод, возвращающий квадратичный коэффициент корреляции
     * @param listX - коллекция, содержащая числовой ряд для Х, @NonNull final List<Double> listY - для Y соответственно .
     * @return correlation  квадратичный коэффициент корреляции
     */
    public static Double getPowCorellation(@NonNull final List<Double> listX, @NonNull final List<Double> listY){
        return Double.parseDouble(df.format(Math.pow(getCorellation(listX, listY),2.0)).replace(',', '.'));
    }


    /**
     * Метод, возвращающий значение при подсчете дельты
     * @param x11, x12, y11, y12 - параметры матрицы для подсчета методом треугольника
     * @return (x11 * y12) - (x12 * y11) дельта
     */
    private static Double getDelta(Double x11, Double x12, Double y11, Double y12) {
        return (x11 * y12) - (x12 * y11);
    }

    /**
     * Метод, возвращающий  a уравнения регрессии
     * @param listX, listY - коллекции, содржащие числовой ряд
     * @return a
     */
    public static Double getRegressionA(@NonNull final List<Double> listX, @NonNull final List<Double> listY) {
        Double x = getSum(listX);
        Double y = getSum(listY);

        Double x_2 = getPowSum(listX);

        Double xy = getMultipleSum(listX, listY);

        Double size = (double) listX.size();

        Double delta = getDelta(x_2, x, x, size);
        Double delta1 = getDelta(xy, y, x, size);
        return delta1 / delta;
    }

    /**
     * Метод, возвращающий  b уравнения регрессии
     * @param listX, listY - коллекции, содржащие числовой ряд
     * @return b
     */
    public static Double getRegressionB(@NonNull final List<Double> listX, @NonNull final List<Double> listY) {
        Double x = getSum(listX);
        Double y = getSum(listY);

        Double x_2 = getPowSum(listX);

        Double xy = getMultipleSum(listX, listY);

        Double size = (double) listX.size();

        Double delta = getDelta(x_2, x, x, size);
        Double delta2 = getDelta(x_2, x, xy, y);
        return delta2 / delta;
    }

    /**
     * Метод, возвращающий  уравнение регрессии
     * @param listX, listY - коллекции, содржащие числовой ряд
     * @return уравнение
     */
    public static String getRegression(@NonNull final List<Double> listX, @NonNull final List<Double> listY) {
        Double a = getRegressionA(listX, listY);
        Double b = getRegressionB(listX, listY);
        return "y=" + df.format(a).replace(',', '.') + "x+" + df.format(b).replace(',', '.');
    }
}
