package ru.hse.todojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class TodoController {
    private static final List<Todo> DEFAULT_TODOS = List.of(new Todo("Помыть посуду"));

    private final ObservableList<Todo> items = FXCollections.observableArrayList(DEFAULT_TODOS);
    @FXML
    private ListView<Todo> listView;
    @FXML
    private TextField newTodoTextField;
    @FXML
    private Button addButton;

    @FXML
    private void initialize() {
        listView.setItems(items);


        newTodoTextField.textProperty()
                .addListener((observable, oldValue, newValue) -> addButton.setDisable(newValue.isBlank()));
        newTodoTextField.setFocusTraversable(false);
    }

    @FXML
    private void onMouseClicked() {
        items.removeAll(listView.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void onAddClicked() {
        String text = newTodoTextField.textProperty().get();
        items.add(new Todo(text));
    }
}
