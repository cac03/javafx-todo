package ru.hse.todojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class TodoApplication extends Application {
    private static final String TITLE = "ToDo application";

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        VBox root = loadLayout(loader);
        primaryStage.setScene(new Scene(root, root.getPrefWidth(), root.getPrefHeight()));
        primaryStage.setMinHeight(root.getPrefHeight());
        primaryStage.setMinWidth(root.getPrefWidth());

        primaryStage.setTitle(TITLE);
        primaryStage.show();
    }

    private VBox loadLayout(FXMLLoader loader) {
        try (InputStream inputStream = getClass().getResourceAsStream("/todo-application.fxml")) {
            return loader.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {
        Application.launch(TodoApplication.class, args);
    }
}
