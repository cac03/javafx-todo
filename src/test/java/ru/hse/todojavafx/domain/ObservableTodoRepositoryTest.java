package ru.hse.todojavafx.domain;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObservableTodoRepositoryTest {
    private final ObservableTodoRepository observableTodoRepository
            = new ObservableTodoRepository(new InMemoryTodoRepository());
    private final ObservableList<Todo> todos = observableTodoRepository.getTodos();

    @Test
    void whenTodoAddedItIsPresentInObservableList() {
        Todo todo = new Todo("my text");

        observableTodoRepository.save(todo);

        assertTrue(todos.contains(todo), () -> todos + " must contain " + todo);
    }

    @Test
    void whenTodoDeletedItIsRemovedFromObservableListToo() {
        Todo todo = new Todo("my text");

        observableTodoRepository.save(todo);
        observableTodoRepository.deleteById(todo.getId());

        assertFalse(todos.contains(todo), () -> todos + " must not contain " + todo);
    }
}