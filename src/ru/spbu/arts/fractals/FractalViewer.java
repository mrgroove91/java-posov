package ru.spbu.arts.fractals;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FractalViewer extends Application {

    public final static int W = 600;
    public final static int H = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Просмотр фигур");

        Pane root = initInterface();
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }

    private Pane initInterface() {
        WritableImage img = new WritableImage(W, H);
        Display display = new Display(-2, 2);
        Fractal f = new Mandelbrot();
        display.paint(img, f, true);

        ImageView imageView = new ImageView(img);
        return new Pane(imageView);
    }
}