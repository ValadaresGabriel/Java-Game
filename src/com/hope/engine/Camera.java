package com.hope.engine;

public class Camera {

    public static int x;

    public static int y;

    public static int cameraLimit(int x, int xMin, int xMax) {
        if (x < xMin)
            x = xMin;

        if (x > xMax)
            x = xMax;

        return x;
    }

}
