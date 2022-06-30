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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilesCollectorJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Каталоги");

        Parent parent = initInterface(stage);
        stage.setScene(new Scene(parent));

        stage.show();

    }

    private Parent initInterface(Stage stage) {
        VBox panel0 = new VBox();

        Button button = new Button("Выбрать каталог");
        Label label = new Label("");
        ListView<String> listView = new ListView<>();
        listView.setEditable(true);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Открыть каталог");
                File dir = directoryChooser.showDialog(stage);
                if (dir != null) {
                    String directory = dir.getAbsolutePath();
                    label.setText(directory);

                    FilesCollector fc = new FilesCollector();
                    Path pathToSearch = Paths.get(directory);
                    try {
                        listView.getItems().clear();
                        List<String> filesDir = searchFiles(fc, pathToSearch);
                        for (String each: filesDir)
                        {

                            listView.getItems().add(each);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    label.setText(null);
                }
            }

        });

        panel0.getChildren().addAll(label, listView, button);
        return panel0;
    }

    public List<String> searchFiles(FilesCollector fc, Path pathToSearch) throws IOException {
        Files.walkFileTree(pathToSearch, fc);
        List<Path> allFiles = fc.getAllFiles();

        List<String> stringFiles = allFiles
                .stream()
                .map(p -> p.toString())
                .collect(Collectors.toList());

        System.out.println(stringFiles.toString());
        return stringFiles;
    }

}
