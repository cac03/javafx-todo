package ru.hse.todojavafx.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ru.hse.todojavafx.domain.Todo;

import java.util.List;

/**
 * Данный класс - JavaFx controller.
 * <p>
 * Его полное имя указано в атрибуте fx:controller fxml файла.
 * Зная его, fxml создает экземпляр данного класса, а затем связывает созданную иерархию объектов с ним.
 * <p>
 * Это включает в себя:
 * 1. Связывание элементов в fxml с полями данного класса, см. {@link TodoController#addButton}, {@link TodoController#newTodoTextField}
 * 2. Вызов методов при определенных действиях, см {@link TodoController#onAddClicked()}, {@link TodoController#onItemClicked()}
 */
public class TodoController {
    private static final List<Todo> DEFAULT_TODOS = List.of(new Todo("Помыть посуду"));

    private final ObservableList<Todo> items = FXCollections.observableArrayList(DEFAULT_TODOS);
    // Соответствует ListView в fxml, см. атрибут fx:id
    @FXML
    private ListView<Todo> listView;
    @FXML
    private TextField newTodoTextField;
    @FXML
    private Button addButton;

    /**
     * Данный метод инициализирует view.
     * <p>
     * Мы не можем сделать это в конструкторе, потому что в нём поля будут {@code null}:
     * fxml проставит их после вызова конструктора, а потом вызовет метод {@code initialize}
     */
    @FXML
    private void initialize() {
        // Устанавливаем список задач для отображения в списке
        listView.setItems(items);
        // Отключаем кнопку добавления при запуске
        addButton.setDisable(true);
        newTodoTextField.textProperty()
                .addListener((observable, oldValue, newValue) ->
                        // При изменении значения в текстовом поле включаем или отключаем кнопку
                        // добавления.
                        // "" -> кнопка отключена
                        // "   " -> тоже
                        // "что-то" -> включена
                        addButton.setDisable(newValue.isBlank()));
        // Если это не сделать, то при запуске курсор будет в поле ввода
        newTodoTextField.setFocusTraversable(false);
    }

    /**
     * Данный метод вызывается при нажатии элемента в списке.
     */
    @FXML
    private void onItemClicked() {
        // Просто удаляем выбранный элемент
        ObservableList<Todo> selectedItems = listView.getSelectionModel().getSelectedItems();
        items.removeAll(selectedItems);
    }

    /**
     * Данный метод вызывается при нажатии на кнопку добавления
     */
    @FXML
    private void onAddClicked() {
        // Добавляем задачу в список
        String text = newTodoTextField.textProperty().getValue();
        items.add(new Todo(text));
    }
}
