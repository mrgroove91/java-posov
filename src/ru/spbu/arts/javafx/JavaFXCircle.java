package ru.spbu.arts.javafx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class JavaFXCircle extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //объект Stage — это окно приложения, у него есть заголок, кнопочки,
        //содержимое
        stage.setTitle("Управление кругом");

        //Parent — узел, который можно использовать как корень для сцены
        Parent parent = initInterface();
        stage.setScene(new Scene(parent));
        //сделать видимым
        stage.show();
    }

    private Parent initInterface() {
        GridPane panel0 = new GridPane();

        Pane panelBlue = new Pane();
        Pane panelYellow = new Pane();
        Pane panelOrange = new Pane();
        Pane panelGreen = new Pane();
        Slider slider = new Slider();
        Label labelRadius = new Label("Радиус круга");
        ColorPicker circleColor = new ColorPicker();
        Label labelCircleColor = new Label("Цвет круга");
        ColorPicker paneColor = new ColorPicker();
        Label labelPaneColor = new Label("Цвет фона");
        Circle circle = new Circle();
/*
        panelBlue.setStyle("-fx-background-color: blue");
        panelYellow.setStyle("-fx-background-color: yellow");
        panelOrange.setStyle("-fx-background-color: orange");
        panelGreen.setStyle("-fx-background-color: green");
        panel0.setStyle("-fx-background-color: black");
*/
        panel0.add(labelRadius, 0, 0);
        panel0.add(panelBlue, 0, 1);
        panel0.add(labelCircleColor, 0, 2);
        panel0.add(panelYellow, 0, 3);
        panel0.add(labelPaneColor, 0, 4);
        panel0.add(panelOrange, 0, 5);
        panel0.add(panelGreen, 1, 0, 1, 6);

        //настройка столбцов
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setMinWidth(150);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPrefWidth(450);
        c2.setHgrow(Priority.ALWAYS);
        panel0.getColumnConstraints().addAll(c1, c2);

        //настройка строк
        RowConstraints r1 = new RowConstraints();
        r1.setPrefHeight(50);
        RowConstraints r2 = new RowConstraints();
        r2.setPrefHeight(20);
        RowConstraints r3 = new RowConstraints();
        r3.setPrefHeight(50);
        RowConstraints r4 = new RowConstraints();
        r4.setPrefHeight(50);
        RowConstraints r5 = new RowConstraints();
        r5.setPrefHeight(50);
        RowConstraints r6 = new RowConstraints();
        r6.setPrefHeight(150);
        r6.setVgrow(Priority.ALWAYS);
        panel0.getRowConstraints().addAll(r1, r2, r3,r4,r5,r6);

        labelRadius.setFont(new Font("Roboto", 18));
        labelCircleColor.setFont(new Font("Roboto", 18));
        labelPaneColor.setFont(new Font("Roboto", 18));
        labelRadius.setMinHeight(30);
        labelCircleColor.setMinHeight(30);
        labelPaneColor.setMinHeight(30);


        panelBlue.getChildren().addAll(slider);
        panelYellow.getChildren().addAll(circleColor);
        panelOrange.getChildren().addAll(paneColor);
        panelGreen.getChildren().addAll(circle);

        circle.setFill(Color.WHITE);

        circle.radiusProperty().bind(Bindings.createDoubleBinding(
                () -> {
                    return slider.getValue();
                },
                slider.valueProperty()
        ));
        circle.centerXProperty().bind(Bindings.createDoubleBinding(
                () -> {
                    return (panelGreen.getWidth() / 2);
                },
                panelGreen.widthProperty()
        ));
        circle.centerYProperty().bind(Bindings.createDoubleBinding(
                () -> {
                    return (panelGreen.getHeight() / 2);
                },
                panelGreen.heightProperty()
        ));

        // create a event handler
        EventHandler<ActionEvent> eventCircle = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // color
                Color c = circleColor.getValue();

                // set text of the label to RGB value of color
                circle.setFill(c);
            }
        };
        // set listener
        circleColor.setOnAction(eventCircle);

        ObjectProperty<Background> background = panelGreen.backgroundProperty();
        background.bind(Bindings.createObjectBinding(
                () -> {
                    BackgroundFill fill = new BackgroundFill(paneColor.getValue(), CornerRadii.EMPTY, Insets.EMPTY);
                    return new Background(fill);
                },
                paneColor.valueProperty()));

        slider.maxProperty().bind(Bindings.createDoubleBinding(
                () -> {

                    return (Math.min(panelGreen.getHeight(),panelGreen.getWidth()) / 2);
                },
                panelGreen.heightProperty(),panelGreen.widthProperty() ));


        panelGreen.setMinHeight(100);
        panelGreen.setMinWidth(100);
        return panel0;
    }

}
