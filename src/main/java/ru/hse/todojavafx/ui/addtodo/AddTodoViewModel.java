package ru.hse.todojavafx.ui.addtodo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.hse.todojavafx.domain.Todo;
import ru.hse.todojavafx.domain.TodoRepository;
import ru.hse.todojavafx.logging.Logger;
import ru.hse.todojavafx.util.Assert;

/**
 * Это - ViewModel для формы добавления новой задачи.
 * <p>
 * Представляет собой модель для представления.
 * То есть содержит свойства, опираясь на которые представление изменяется:
 * <ol>
 *     <li>{@link #enteredTextProperty()} - введенный в поле текст</li>
 *     <li>{@link #canSaveEnteredTodoProperty()} - флаг, показывающий можно ли сохранить введенную задачу.
 *     Если нет, то кнопка добавления будет заблокирована в {@link AddTodoView}</li>
 * </ol>
 * <p>
 * Один и тот же экземпляр {@link AddTodoView} может быть использован для нескольких представлений.
 * <p>
 * В приложении он создается в {@link ru.hse.todojavafx.factory.TodoApplicationControllerFactory}, а затем используется
 * в {@link AddTodoView}.
 * <p>
 * В MVVM важно связывание свойств (Binding) - механизм при котором данные разных компонент могут связываться друг с другом.
 * <p>
 * Если запустить приложение, то можно увидеть несколько форм добавления задачи.
 * При вводе текста в одно поле, он же отобразится и в других. Это происходит из-за связывания полей ввода
 * с {@link #enteredTextProperty()} (см. {@link AddTodoView#initialize()}
 * <p>
 * Связывание происходит автоматически благодаря набору {@code *Property} классов из JavaFX
 */
public class AddTodoViewModel {
    private static final Logger logger = Logger.forClass(AddTodoViewModel.class);

    /**
     * Введенный в поле текст
     */
    private final StringProperty enteredText = new SimpleStringProperty();
    /**
     * Можно ли сохранять введенную задачу
     */
    private final BooleanProperty canSaveEnteredTodo = new SimpleBooleanProperty();
    /**
     * Репозиторий для сохранения введенной задачи
     */
    private final TodoRepository todoRepository;

    public AddTodoViewModel(TodoRepository todoRepository) {
        Assert.notNull(todoRepository, "todoRepository == null");
        canSaveEnteredTodo.bind(enteredText.isEmpty());
        this.todoRepository = todoRepository;
    }

    public StringProperty enteredTextProperty() {
        return enteredText;
    }

    public BooleanProperty canSaveEnteredTodoProperty() {
        return canSaveEnteredTodo;
    }

    public void saveEnteredTodo() {
        String text = enteredText.getValueSafe();
        Assert.state(!text.isEmpty(), () -> "Entered text is empty");
        logger.debug("Saving todo with text = %s", text);

        todoRepository.save(new Todo(text));
        // Сбрасываем введенный текст после того, как задача была сохранена
        enteredText.set("");
    }
}
