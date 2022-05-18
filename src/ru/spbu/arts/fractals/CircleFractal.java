package ru.spbu.arts.fractals;

public class CircleFractal implements Fractal {
    @Override
    public Position getZoom() {
        return null;
    }

    @Override
    public double toNumber(double x, double y) {
        if (x * x + y * y <= 1)
            return 0;
        else
            return 1;
    }
}