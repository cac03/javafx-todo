package ru.hse.todojavafx.domain;

import ru.hse.todojavafx.logging.Logger;
import ru.hse.todojavafx.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryTodoRepository implements TodoRepository {
    private static final Logger logger = Logger.forClass(InMemoryTodoRepository.class);

    private final Map<UUID, Todo> todos = new LinkedHashMap<>();

    @Override
    public Optional<Todo> findById(UUID id) {
        Assert.notNull(id, "id == null");

        Todo todo = todos.get(id);
        return Optional.ofNullable(todo);
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(todos.values());
    }

    @Override
    public void save(Todo todo) {
        Assert.notNull(todo, "todo == null");

        if (todo.getId() == null) {
            todo.setId(UUID.randomUUID());
        }
        todos.put(todo.getId(), todo);
        logger.debug("Saved todo = %s, id = %s", todo, todo.getId());
    }

    @Override
    public void deleteById(UUID id) {
        Assert.notNull(id, "id == null");
        todos.remove(id);
        logger.debug("Deleted by id = %s", id);
    }

    @Override
    public void deleteByIds(Collection<UUID> ids) {
        Assert.notNull(ids, "ids == null");
        logger.trace("deleteByIds(%s)", ids);

        ids.forEach(this::deleteById);
    }

    @Override
    public void deleteAll() {
        todos.clear();
    }
}
