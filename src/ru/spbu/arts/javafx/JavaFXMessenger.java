package ru.spbu.arts.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXMessenger extends Application {

    @Override
    public void start(Stage stage) {
        //объект Stage — это окно приложения, у него есть заголок, кнопочки,
        //содержимое
        stage.setTitle("Месенджер");

        //Parent — узел, который можно использовать как корень для сцены
        Parent parent = initInterface();
        stage.setScene(new Scene(parent));

        //сделать видимым
        stage.show();
    }

    private void SubmitMessage(TextArea textArea, TextField textField){
        if (textArea.getText().equals("")) {
            textArea.setText(textField.getText());
        } else {
            textArea.setText(textArea.getText() + "\n" + textField.getText());
        }
    }

    private Parent initInterface() {
        HBox panel0 = new HBox();

        VBox panel1 = new VBox();
        VBox panel2 = new VBox();
        TextArea textArea = new TextArea();
        HBox panel12 = new HBox();
        TextField textField = new TextField();
        Button button = new Button("Послать");
        Label label = new Label("Контакты");
        ListView<String> listView = new ListView<>();

        /*
        panel0.setStyle("-fx-background-color: black");
        panel1.setStyle("-fx-background-color: blue");
        panel2.setStyle("-fx-background-color: cyan");
        textArea.setStyle("-fx-background-color: red");
        panel12.setStyle("-fx-background-color: gray");
        textField.setStyle("-fx-background-color: lightcoral");
        //button.setStyle("-fx-background-color: magenta");
        label.setStyle("-fx-background-color: darkorange");
        listView.setStyle("-fx-background-color: gold");
        */

        panel0.getChildren().addAll(panel1, panel2);
        panel1.getChildren().addAll(textArea, panel12);
        panel12.getChildren().addAll(textField, button);
        panel2.getChildren().addAll(label, listView);

        HBox.setHgrow(panel1, Priority.ALWAYS);
        HBox.setHgrow(textField, Priority.ALWAYS);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        VBox.setVgrow(listView, Priority.ALWAYS);
        label.setFont(new Font("Roboto", 18));
        button.setFont(new Font("Roboto", 18));
        button.setPrefWidth(100);
        button.setPrefHeight(100);
        panel2.setPrefWidth(200);
        textField.setPrefHeight(100);
        label.setPrefHeight(30);
        label.setPrefWidth(200);


        listView.getItems().add("Владислав Пельш");
        listView.getItems().add("Валерий Меладзе");
        listView.getItems().add("Константин Эрнст");
        listView.getItems().add("Владимир Познер");
        textArea.setEditable(false);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SubmitMessage(textArea, textField);
            }

        });

        panel0.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    SubmitMessage(textArea, textField);
                }
            }
        });

        return panel0;
    }
}