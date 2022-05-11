package ru.spbu.arts.javafx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedWriter;
import java.lang.reflect.InaccessibleObjectException;
import java.net.URL;
import java.nio.file.Path;

public class JavaFXImage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Работа с изображениями и цветом");
        Parent parent = initInterface();
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public static Color wavelengthToRgb(int wInt) {
        double gamma = 0.8;
        double R;
        double G;
        double B;
        double attenuation;
        double wavelength = wInt;
        if (wavelength >= 380 & wavelength <= 440) {
            attenuation = 0.3 + 0.7 * (wavelength - 380) / (440 - 380);
            R = Math.pow(((-(wavelength - 440) / (440 - 380)) * attenuation), gamma);
            G = 0.0;
            B = Math.pow((attenuation), gamma);
        } else if (wavelength >= 440 & wavelength <= 490) {
            R = 0.0;
            G = Math.pow(((wavelength - 440) / (490 - 440)), gamma);
            B = 1.0;
        } else if (wavelength >= 490 & wavelength <= 510) {
            R = 0.0;
            G = 1.0;
            B = Math.pow((-(wavelength - 510) / (510 - 490)), gamma);
        } else if (wavelength >= 510 & wavelength <= 580) {
            R = Math.pow(((wavelength - 510) / (580 - 510)), gamma);
            G = 1.0;
            B = 0.0;
        } else if (wavelength >= 580 & wavelength <= 645) {
            R = 1.0;
            G = Math.pow((-(wavelength - 645) / (645 - 580)), gamma);
            B = 0.0;
        } else if (wavelength >= 645 & wavelength <= 750) {
            attenuation = 0.3 + 0.7 * (750 - wavelength) / (750 - 645);
            R = Math.pow((attenuation), gamma);
            G = 0.0;
            B = 0.0;
        } else {
            R = 0.0;
            G = 0.0;
            B = 0.0;
        }


        int intR = (int) Math.floor(R * 255);
        int intG = (int) Math.floor(G * 255);
        int intB = (int) Math.floor(B * 255);

        return (Color.rgb(intR, intG, intB,1));
    }

    public static Color colorFromLCH(double cieL, double cieC, double cieH) {
        // CIE-LCH -> CIE-Lab
        // http://www.easyrgb.com/en/math.php
        double cieHradians = cieH * Math.PI / 180;
        double ciea = Math.cos(cieHradians) * cieC;
        double cieb = Math.sin(cieHradians) * cieC;

        // CIE-Lab -> XYZ
        double varY = (cieL + 16) / 116;
        double varX = ciea / 500 + varY;
        double varZ = varY - cieb / 200;

        if (varY * varY * varY > 0.008856)
            varY = varY * varY * varY;
        else
            varY = (varY - 16. / 116) / 7.787;
        if (varX * varX * varX > 0.008856)
            varX = varX * varX * varX;
        else
            varX = (varX - 16. / 116) / 7.787;
        if (varZ * varZ * varZ > 0.008856)
            varZ = varZ * varZ * varZ;
        else
            varZ = (varZ - 16. / 116) / 7.787;

        double X = varX * 95.047;
        double Y = varY * 100;
        double Z = varZ * 108.883;

        //XYZ -> sRGB
        varX = X / 100;
        varY = Y / 100;
        varZ = Z / 100;

        double varR = varX * 3.2406 + varY * -1.5372 + varZ * -0.4986;
        double varG = varX * -0.9689 + varY * 1.8758 + varZ * 0.0415;
        double varB = varX * 0.0557 + varY * -0.2040 + varZ * 1.0570;

        if (varR > 0.0031308)
            varR = 1.055 * Math.pow(varR, 1 / 2.4) - 0.055;
        else
            varR = 12.92 * varR;
        if (varG > 0.0031308)
            varG = 1.055 * Math.pow(varG, 1 / 2.4) - 0.055;
        else
            varG = 12.92 * varG;
        if (varB > 0.0031308)
            varB = 1.055 * Math.pow(varB, 1 / 2.4) - 0.055;
        else
            varB = 12.92 * varB;

        if (varR < 0)
            varR = 0;
        if (varR > 1)
            varR = 1;
        if (varG < 0)
            varG = 0;
        if (varG > 1)
            varG = 1;
        if (varB < 0)
            varB = 0;
        if (varB > 1)
            varB = 1;

        return Color.color(varR, varG, varB);
    }

    private Parent initInterface() {
        WritableImage wImage1 = new WritableImage(100, 100);
        PixelWriter pixelWriter = wImage1.getPixelWriter();
        for (int x = 0; x < 99; x++)
            for (int y = 0; y < 99; y++) {
                pixelWriter.setColor(x, y, Color.rgb(0, 255, 196));
            }
        ImageView viewer1 = new ImageView(wImage1);

        WritableImage wImage2 = new WritableImage(256, 256);
        PixelWriter pixelWriter2 = wImage2.getPixelWriter();
        for (int x = 0; x < 255; x++)
            for (int y = 0; y < 255; y++) {
                pixelWriter2.setColor(x, y, Color.rgb(x, 255 - y, 200));
            }
        ImageView viewer2 = new ImageView(wImage2);

        WritableImage wImage3 = new WritableImage(360, 100);
        PixelWriter pixelWriter3 = wImage3.getPixelWriter();
        double yFloat;
        for (int x = 0; x < 359; x++)
            for (int y = 0; y < 99; y++) {
                yFloat = y;
                pixelWriter3.setColor(x, y, Color.hsb(x, yFloat / 100, 1));
            }
        ImageView viewer3 = new ImageView(wImage3);

        WritableImage wImage4 = new WritableImage(100, 100);
        PixelWriter pixelWriter4 = wImage4.getPixelWriter();
        for (int x = 0; x < 99; x++)
            for (int y = 0; y < 99; y++) {
                if ((x > 25 & x < 75) && (y > 25 & y < 75)) {
                    pixelWriter4.setColor(x, y, Color.BLACK);
                } else {
                    pixelWriter4.setColor(x, y, Color.WHITE);
                }
            }
        ImageView viewer4 = new ImageView(wImage4);

        WritableImage wImage5 = new WritableImage(100, 100);
        PixelWriter pixelWriter5 = wImage5.getPixelWriter();
        double radius;
        for (int x = 0; x < 99; x++)
            for (int y = 0; y < 99; y++) {
                radius = Math.sqrt(Math.pow(x - 50, 2) + Math.pow(y - 50, 2));
                if (radius < 50) {
                    pixelWriter5.setColor(x, y, Color.BLACK);
                } else {
                    pixelWriter5.setColor(x, y, Color.WHITE);
                }
            }
        ImageView viewer5 = new ImageView(wImage5);

        WritableImage wImage6 = new WritableImage(360, 100);
        PixelWriter pixelWriter6 = wImage6.getPixelWriter();
        double xFloatLCH;
        double yFloatLCH;
        for (int x = 0; x < 359; x++)
            for (int y = 0; y < 99; y++) {
                xFloatLCH = x;
                yFloatLCH = y;
                pixelWriter6.setColor(x, y, colorFromLCH(yFloatLCH, 132, xFloatLCH));
                //System.out.println(xFloatLCH/ 360);
            }
        ImageView viewer6 = new ImageView(wImage6);

        WritableImage wImage7 = new WritableImage(380, 100);
        PixelWriter pixelWriter7 = wImage7.getPixelWriter();
        for (int x = 0; x < 380; x++)
            for (int y = 0; y < 99; y++) {
                pixelWriter7.setColor(x, y, wavelengthToRgb(x+380));
            }
        ImageView viewer7 = new ImageView(wImage7);

        return new FlowPane( // панелька выставляет друг за другом, слева направо, сверху вниз
                viewer1,
                viewer2,
                viewer3,
                viewer4,
                viewer5,
                viewer6,
                viewer7
        );

    }
}
