package ru.spbu.arts.fractals;

public interface Fractal {
    Position getZoom();
    //для каждой точки выдаётся число от 0 до 1
    double toNumber(double x, double y);


}