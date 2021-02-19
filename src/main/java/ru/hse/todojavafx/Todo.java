package ru.hse.todojavafx;

import java.util.Objects;

public class Todo {
    private final String text;

    public Todo(String text) {
        this.text = text;
        Objects.requireNonNull(text);
        if (text.isBlank()) {
            throw new IllegalArgumentException("text is blank");
        }
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
