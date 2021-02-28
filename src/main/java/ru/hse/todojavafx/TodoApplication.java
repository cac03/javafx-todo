package ru.hse.todojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.hse.todojavafx.factory.TodoApplicationControllerFactory;

import java.io.IOException;

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
        loader.setControllerFactory(new TodoApplicationControllerFactory());
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
        try {
            loader.setLocation(getClass().getResource(FXML_FILE_PATH));
            return loader.load();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
