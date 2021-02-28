package ru.hse.todojavafx.domain;

import ru.hse.todojavafx.util.Assert;

import java.util.UUID;

public class Todo {
    private String text;
    private UUID id;

    public Todo(String text) {
        Assert.notNull(text, "text == null");
        Assert.isTrue(!text.isEmpty(), () -> "text is empty");
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        Assert.state(this.id == null, () -> "id already set, this.id = " + this.id + ", id = " + id);
        this.id = id;
    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        Assert.notNull(text, "text == null");
        Assert.isTrue(!text.isBlank(), () -> "text is blank");
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
