package ru.hse.todojavafx.factory;

import javafx.util.Callback;
import ru.hse.todojavafx.domain.InMemoryTodoRepository;
import ru.hse.todojavafx.domain.ObservableTodoRepository;
import ru.hse.todojavafx.ui.TodoController;
import ru.hse.todojavafx.ui.addtodo.AddTodoView;
import ru.hse.todojavafx.ui.addtodo.AddTodoViewModel;

/**
 * Данный класс необходим для контроля процесса создания контроллеров JavaFX.
 * <p>
 * Ввиду наличия зависимостей у
 * <ul>
 *     <li>{@link AddTodoView}</li>
 *     <li>{@link TodoController}</li>
 * </ul>
 * на другие компоненты, нельзя создание контроллеров возложить на JavaFX, поскольку для работы этого механизма
 * нужны конструкторы по умолчанию.
 * <p>
 * Данный класс используется в {@link ru.hse.todojavafx.TodoApplication}
 */
public class TodoApplicationControllerFactory implements Callback<Class<?>, Object> {

    private final InMemoryTodoRepository todoRepository = new InMemoryTodoRepository();
    private final ObservableTodoRepository observableTodoRepository = new ObservableTodoRepository(todoRepository);
    private final AddTodoViewModel addTodoViewModel = new AddTodoViewModel(observableTodoRepository);

    @Override
    public Object call(Class<?> param) {
        //noinspection ChainOfInstanceofChecks
        if (param == TodoController.class) {
            return new TodoController(observableTodoRepository);
        } else if (param == AddTodoView.class) {
            return new AddTodoView(addTodoViewModel);
        }
        throw new IllegalArgumentException("Unsupported controller class = " + param);
    }
}
