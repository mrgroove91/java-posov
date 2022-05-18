package ru.spbu.arts.fractals;

public class GradientCircleFractal implements Fractal {
    @Override
    public Position getZoom() {
        return null;
    }

    @Override
    public double toNumber(double x, double y) {
        //цвет соответстовал расстоянию до нуля
        double r = x * x + y * y; //Math.sqrt(x * x + y * y);
        //когда r = 0, будет черный
        //когда r >= 1, будет белый
        //промежуточные варианты — серые цвета

        if (r > 1)
            r = 1;

        return r;
    }
}
