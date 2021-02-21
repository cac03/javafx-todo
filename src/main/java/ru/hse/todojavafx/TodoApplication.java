package ru.hse.todojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Не запускайте {@code main} из этого класса!
 * Делайте это через {@link Main}
 */
public class TodoApplication extends Application {
    /**
     * Путь к fxml файлу, он - в директории resources
     */
    private static final String FXML_FILE_PATH = "/todo-application.fxml";
    private static final String TITLE = "ToDo application";

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        VBox root = loadLayout(loader);
        primaryStage.setScene(new Scene(root));

        // См. prefHeight / prefWidth в .fxml у VBox
        primaryStage.setMinHeight(root.getPrefHeight());
        primaryStage.setMinWidth(root.getPrefWidth());

        primaryStage.setTitle(TITLE);
        primaryStage.show();
    }

    /**
     * Загрузить иерархию объектов основного окна из файла, используя {@code loader}
     *
     * @param loader с помощью которого загружать
     * @return корневой элемент. Это - VBox
     * @throws IllegalStateException если произошла ошибка ввода-вывода
     */
    private VBox loadLayout(FXMLLoader loader) {
        try (InputStream inputStream = getClass().getResourceAsStream(FXML_FILE_PATH)) {
            return loader.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
