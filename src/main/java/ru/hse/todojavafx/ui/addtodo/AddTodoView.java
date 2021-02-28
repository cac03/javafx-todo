package ru.hse.todojavafx.ui.addtodo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.hse.todojavafx.logging.Logger;
import ru.hse.todojavafx.util.Assert;

/**
 * {@link AddTodoView} - представление формы добавления задачи.
 * <p>
 * Представление содержит ссылки на элементы пользовательского интерфейса:
 * <ol>
 *     <li>
 *         {@link #textField} - поле ввода в данной форме (не текст, а именно поле, UI-control)
 *     </li>
 *     <li>
 *         {@link #addButton} - кнопка добавления в данной форме. Тоже UI-control
 *     </li>
 * </ol>
 * <p>
 * Представление содержит ссылку на ViewModel (т.е. {@link AddTodoViewModel})
 * и связывается с его свойствами в методе {@link #initialize()}.
 * <p>
 * Для каждой формы в пользовательском интерфейсе создается новый экземпляр данного класса в
 * {@link ru.hse.todojavafx.factory.TodoApplicationControllerFactory}
 */
public class AddTodoView {
    private static final Logger logger = Logger.forClass(AddTodoView.class);

    /**
     * Поле ввода текста новой задачи
     */
    @FXML
    private TextField textField;
    /**
     * Кнопка добавления новой задачи
     */
    @FXML
    private Button addButton;

    /**
     * ViewModel, с которым связано текущее представление
     */
    private final AddTodoViewModel viewModel;

    /**
     * Вызывается в {@link ru.hse.todojavafx.factory.TodoApplicationControllerFactory}
     */
    public AddTodoView(AddTodoViewModel viewModel) {
        Assert.notNull(viewModel, "viewModel == null");
        this.viewModel = viewModel;
    }

    @FXML
    void initialize() {
        viewModel.enteredTextProperty()
                .bindBidirectional(textField.textProperty());
        addButton.disableProperty()
                .bind(viewModel.canSaveEnteredTodoProperty());
    }

    @FXML
    private void onAddClicked() {
        logger.trace("onAddClicked");
        viewModel.saveEnteredTodo();
    }
}
