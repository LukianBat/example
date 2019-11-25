package com.movavi.android.geophysics;

import com.movavi.android.geophysics.core.MyMath;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * парам2 - парам1 - рассчет
 */
public class MathUnitTest {
    private final List<Double> listX = new ArrayList<>();
    private final List<Double> listY = new ArrayList<>();

    @Before
    public void setUpX() {
        for (double i = 1.0; i < 19;i++) {
            listX.add(i);
        }
    }

    @Before
    public void setUpY() {
        listY.add(100.7956626);
        listY.add(103.5471298);
        listY.add(95.53852555);
        listY.add(93.12672217);
        listY.add(92.41055232);
        listY.add(92.23219375);
        listY.add(90.5241125);
        listY.add(91.19173494);
        listY.add(91.73713301);
        listY.add(80.53954142);
        listY.add(87.22741592);
        listY.add(80.84796968);
        listY.add(74.30910538);
        listY.add(81.33006362);
        listY.add(77.22439308);
        listY.add(70.8818502);
        listY.add(75.85567486);
        listY.add(70.57441372);
    }

    @Test
    public void correlationTest() {
        double corel = MyMath.getCorellation(listX, listY);
        Assert.assertEquals(-0.955, corel, 0.01);

    }

    @Test
    public void correlationPowTest() {
        double corel = MyMath.getPowCorellation(listX, listY);
        Assert.assertEquals(0.912, corel, 0.01);

    }

    @Test
    public void regressionTestA() {
        double regression = MyMath.getRegressionA(listX, listY);
        Assert.assertEquals(-1.7763, regression, 0.01);
    }

    @Test
    public void regressionTestB() {
        double regression = MyMath.getRegressionB(listX, listY);
        Assert.assertEquals(102.9803, regression, 0.01);
    }
}