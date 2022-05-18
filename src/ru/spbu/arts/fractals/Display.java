package ru.spbu.arts.fractals;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

//Хранит информацию о положении окна на математической плоскости
//Умеет создавать изображения на основе фрактала
public class Display {
    private double x0;
    private double y0;
    private Position zoom;
    public Display(double x0, double y0) {
        this.x0 = x0;
        this.y0 = y0;

    }

    // Рисует указанный фрактал на переданном изображении
    public void paint(WritableImage image, Fractal fractal, Boolean gray) {
        double w = image.getWidth();
        double h = image.getHeight();
        zoom = fractal.getZoom().resize(w, h);
        Palette chroma = new HSBPalette();
        Palette noChroma = new GrayscalePalette();
        Color color;
        PixelWriter pixelWriter = image.getPixelWriter();

        for (int xPixel = 0; xPixel < w; xPixel++)
            for (int yPixel = 0; yPixel < h; yPixel++) {
                double x = zoom.getXMin() + (xPixel + 0.5) / w * (zoom.getXMax() - zoom.getXMin());
                double y = zoom.getYMax() - (yPixel + 0.5) / h * (zoom.getYMax() - zoom.getYMin());
                if (gray){
                    color = noChroma.Paint(fractal.toNumber(x, y));
                }
                else{
                    color = chroma.Paint(fractal.toNumber(x, y));
                }

                pixelWriter.setColor(xPixel, yPixel, color);
            }
    }
}