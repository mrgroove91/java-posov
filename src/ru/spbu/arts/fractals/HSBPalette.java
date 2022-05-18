package ru.spbu.arts.fractals;

import javafx.scene.paint.Color;

public class HSBPalette implements Palette{
    @Override
    public Color Paint(double r) {
        return Color.hsb(r*360,1,1);
    }
}
