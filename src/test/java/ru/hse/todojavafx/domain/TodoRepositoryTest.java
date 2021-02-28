package ru.hse.todojavafx.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoRepositoryTest {
    private final TodoRepository todoRepository = new InMemoryTodoRepository();

    @BeforeEach
    void deleteAll() {
        todoRepository.deleteAll();
    }

    @Test
    void repositoryHasNoItemsInitially() {
        assertTrue(todoRepository.findAll().isEmpty());
    }

    @Test
    void todoSuccessfullySaved() {
        Todo todo = new Todo("My text");
        todoRepository.save(todo);

        assertNotNull(todo.getId());
        assertTrue(todoRepository.findById(todo.getId()).isPresent());
    }

    @Test
    void todoSuccessfullyUpdated() {
        Todo todo = new Todo("My text");
        todoRepository.save(todo);

        assertNotNull(todo.getId());

        String newText = "new text";
        todo.setText(newText);

        todoRepository.save(todo);

        Optional<Todo> newTodo = todoRepository.findById(todo.getId());
        assertTrue(newTodo.isPresent());
        assertEquals(newText, newTodo.orElseThrow().getText());
    }

    @Test
    void todoDeletedById() {
        Todo todo = new Todo("My text");
        todoRepository.save(todo);

        todoRepository.deleteById(todo.getId());

        assertTrue(todoRepository.findById(todo.getId()).isEmpty());
    }

    @Test
    void todosDeletedByIds() {
        List<Todo> todos = List.of(new Todo("1"), new Todo("2"), new Todo("3"));
        todos.forEach(todoRepository::save);

        Set<UUID> ids = todos.stream()
                .map(Todo::getId)
                .collect(Collectors.toUnmodifiableSet());
        todoRepository.deleteByIds(ids);

        assertTrue(todoRepository.findAll().isEmpty());
    }

    @Test
    void allTodosFound() {
        int count = 3;
        IntStream.range(0, count)
                .mapToObj(it -> it + "")
                .map(Todo::new)
                .forEach(todoRepository::save);

        List<Todo> todos = todoRepository.findAll();

        assertEquals(count, todos.size());
    }
}