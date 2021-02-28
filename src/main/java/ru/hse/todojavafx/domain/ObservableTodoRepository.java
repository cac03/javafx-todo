package ru.hse.todojavafx.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.hse.todojavafx.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * {@link ObservableTodoRepository} - реализация интерфейса {@link TodoRepository},
 * которая является декоратором.
 * <p>
 * То есть она имеет ссылку на другой {@link TodoRepository}, через который выполняются все операции, но при этом здесь
 * добавляется функциональность отслеживания всех изменений коллекции задач.
 * <p>
 * Данный репозиторий необходим, чтобы пользовательский интерфейс мог единым образом отслеживать изменения
 * коллекции задач. Для того, чтобы оповещать интерфейс об изменениях используется {@link ObservableList} -
 * особый {@link List}, который умеет оповещать других об изменениях в нём.
 * <p>
 * См также:
 * <ul>
 *     <li>
 *         {@link ru.hse.todojavafx.ui.TodoController#TodoController(ObservableTodoRepository)} -
 *         конструктор принимает именно {@link ObservableTodoRepository}, потому что для доступа
 *         к {@link ObservableList} необходим метод {@link #getTodos()}, которого в интерфейсе нет
 *     </li>
 *     <li>
 *         {@link ru.hse.todojavafx.factory.TodoApplicationControllerFactory} - именно здесь создаётся единый экземпляр данного класса
 *     </li>
 * </ul>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Decorator_pattern#Java">Decorator (Wikipedia)</a>
 */
public class ObservableTodoRepository implements TodoRepository {
    private final TodoRepository targetRepository;
    private final ObservableList<Todo> todos = FXCollections.observableArrayList();

    public ObservableTodoRepository(TodoRepository targetRepository) {
        Assert.notNull(targetRepository, "todoRepository == null");
        this.targetRepository = targetRepository;
        todos.addAll(targetRepository.findAll());
    }

    @Override
    public Optional<Todo> findById(UUID id) {
        return targetRepository.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return targetRepository.findAll();
    }

    @Override
    public void save(Todo todo) {
        Assert.notNull(todo, "todo == null");
        targetRepository.save(todo);
        todos.add(todo);
    }

    @Override
    public void deleteById(UUID id) {
        Assert.notNull(id, "id == null");
        targetRepository.deleteById(id);
        todos.removeIf(todo -> id.equals(todo.getId()));
    }

    @Override
    public void deleteByIds(Collection<UUID> ids) {
        Assert.notNull(ids, "ids == null");
        targetRepository.deleteByIds(ids);
        todos.removeIf(todo -> ids.contains(todo.getId()));
    }

    @Override
    public void deleteAll() {
        targetRepository.deleteAll();
        todos.clear();
    }

    public ObservableList<Todo> getTodos() {
        return FXCollections.unmodifiableObservableList(todos);
    }
}
