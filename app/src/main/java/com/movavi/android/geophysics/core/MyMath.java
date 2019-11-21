package com.movavi.android.geophysics.core;


import java.util.List;


public class MyMath {

    /*сумма элементов в коллекции*/
    private static Double getSum(List<Double> list) {
        Double sum = 0.0;
        for (Double f : list) {
            sum += f;
        }
        return sum;
    }

    /*сумма элементов в коллекции, возведенных в квадрат */
    private static Double getPowSum(List<Double> list) {
        Double sum = 0.0;
        for (Double elem : list) {
            sum += Math.pow(elem, 2.0);
        }
        return sum;
    }

    /*сумма элементов двух коллекций, предворительно умноженных друг на друга */
    private static Double getMultipleSum(List<Double> listX, List<Double> listY) {
        Double sum = 0.0;
        int i = 0;
        for (Double elem : listX) {
            sum += listX.get(i) * listY.get(i);
            i++;
        }
        return sum;
    }


    /* средне-арифметическое по столбцу=математическое ожидание */
    public static Double getAvg(List<Double> list) {
        return getSum(list) / list.size();
    }


    /**********************************************************
     * Коэффициент корреляции
     ***********************************************************/
    public static Double getCorellation(List<Double> listX, List<Double> listY) {
        /*вычисляем мат. ожидание для x и y*/
        Double avgX = getAvg(listX);
        Double avgY = getAvg(listY);
        /*коварация и сигмы*/
        Double covariance = 0.0;
        Double sigmaX = 0.0;
        Double sigmaY = 0.0;
        /*кореляция*/
        Double correlation = 0.0;

        int i = 0;

        for (Double l : listX) {
            /*вычисляем коварацию для числового ряда*/
            covariance += (listX.get(i) - avgX) * (listY.get(i) - avgY);
            /*вычисляем сигмы (x-мат.ожидание(x))^2*/
            sigmaX += Math.pow(listX.get(i) - avgX, 2.0);
            sigmaY += Math.pow(listY.get(i) - avgY, 2.0);
            i++;
        }
        /* кореляция = коварация/корень квадратный от произведения сигм*/
        correlation = covariance / Math.sqrt(sigmaX * sigmaY);
        return correlation;
    }


    private static Double getDelta(Double x11, Double x12, Double y11, Double y12) {
        return (x11 * y12) - (x12 * y11);
    }


    /**********************************************************
     * Уравнение регрессии
     * Для построение уравнения линейной регрессии вида  используем метод наименьших квадратов.
     * 1. для каждого X и Y находим x^2,xy,y^2
     * 2.составляем уравнение вида | xy=x^2*a+xb
     *                             | y= xa +bn
     * 3.решаем уравнение методом треугольника
     ***********************************************************/

    public static String getRegression(List<Double> listX, List<Double> listY) {
        Double x = getSum(listX);
        Double y = getSum(listY);

        Double x_2 = getPowSum(listX);
        Double y_2 = getPowSum(listY);

        Double xy = getMultipleSum(listX, listY);

        Double size = Double.valueOf(listX.size());

        Double delta = getDelta(x_2, x, x, size);
        Double delta1 = getDelta(xy, y, x, size);
        Double delta2 = getDelta(x_2, x, xy, y);
        Double a = delta1 / delta;
        Double b = delta2 / delta;
        return "y=" + a + "x+" + b;
    }


}
