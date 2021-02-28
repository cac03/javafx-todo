package ru.hse.todojavafx.ui.addtodo;

import org.junit.jupiter.api.Test;
import ru.hse.todojavafx.domain.InMemoryTodoRepository;
import ru.hse.todojavafx.domain.Todo;
import ru.hse.todojavafx.domain.TodoRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddTodoViewModelTest {
    private final TodoRepository todoRepository = new InMemoryTodoRepository();
    private final AddTodoViewModel viewModel = new AddTodoViewModel(todoRepository);

    @Test
    void whenEnteredTextIsEmptyThenAddButtonIsDisabled() {
        String emptyString = "";

        viewModel.enteredTextProperty().set(emptyString);

        assertTrue(viewModel.canSaveEnteredTodoProperty().get());
    }

    @Test
    void whenEnteredTextIsNotEmptyThenAddButtonIsNotDisabled() {
        String emptyString = "my text";

        viewModel.enteredTextProperty().set(emptyString);

        assertFalse(viewModel.canSaveEnteredTodoProperty().get());
    }

    @Test
    void todoSuccessfullySaved() {
        String expectedText = "my text";

        viewModel.enteredTextProperty().set(expectedText);
        viewModel.saveEnteredTodo();

        List<Todo> allTodos = todoRepository.findAll();
        assertEquals(1, allTodos.size());
        assertEquals(expectedText, allTodos.get(0).getText());
    }

    @Test
    void whenTodoSavedEnteredTextIsClearedAndAddButtonIsDisabled() {
        String expectedText = "my text";

        viewModel.enteredTextProperty().set(expectedText);
        viewModel.saveEnteredTodo();

        assertEquals("", viewModel.enteredTextProperty().get());
    }
}