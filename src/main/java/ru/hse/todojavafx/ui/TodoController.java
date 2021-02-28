package ru.hse.todojavafx.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ru.hse.todojavafx.domain.ObservableTodoRepository;
import ru.hse.todojavafx.domain.Todo;
import ru.hse.todojavafx.logging.Logger;
import ru.hse.todojavafx.util.Assert;

/**
 * Данный класс - JavaFx controller.
 * <p>
 * Его полное имя указано в атрибуте fx:controller fxml файла.
 * Зная его, fxml создает экземпляр данного класса через {@link ru.hse.todojavafx.factory.TodoApplicationControllerFactory},
 * а затем связывает созданную иерархию объектов с ним.
 * <p>
 * Это включает в себя:
 * 1. Связывание элементов в fxml с полями данного класса, см. {@link #listView}
 * 2. Вызов методов при определенных действиях, см., {@link TodoController#onItemClicked()}
 */
public class TodoController {
    private static final Logger logger = Logger.forClass(TodoController.class);

    // Соответствует ListView в fxml, см. атрибут fx:id
    @FXML
    private ListView<Todo> listView;
    private final ObservableTodoRepository todoRepository;

    public TodoController(ObservableTodoRepository todoRepository) {
        Assert.notNull(todoRepository, "todoRepository == null");
        this.todoRepository = todoRepository;
    }

    /**
     * Данный метод инициализирует view.
     * <p>
     * Мы не можем сделать это в конструкторе, потому что в нём поля будут {@code null}:
     * fxml проставит их после вызова конструктора, а потом вызовет метод {@code initialize}
     */
    @FXML
    private void initialize() {
        // Устанавливаем список задач для отображения в списке
        listView.setItems(todoRepository.getTodos());
    }

    /**
     * Данный метод вызывается при нажатии элемента в списке.
     */
    @FXML
    private void onItemClicked() {
        // Просто удаляем выбранный элемент
        Todo selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        todoRepository.deleteById(selectedItem.getId());
    }
}
