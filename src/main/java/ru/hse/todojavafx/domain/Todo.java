package ru.hse.todojavafx.domain;

import ru.hse.todojavafx.util.Assert;

public record Todo(String text) {
    public Todo {
        Assert.notNull(text, "text == null");
        Assert.isTrue(!text.isEmpty(), () -> "text is empty");
    }

    @Override
    public String toString() {
        return text;
    }
}
