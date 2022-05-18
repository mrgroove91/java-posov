package ru.spbu.arts.fractals;

import javafx.scene.paint.Color;

public class GrayscalePalette implements Palette{
    @Override
    public Color Paint(double r) {
        return Color.gray(r);
    }
}
