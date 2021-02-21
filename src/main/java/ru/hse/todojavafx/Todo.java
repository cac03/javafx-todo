package ru.hse.todojavafx;

public class Todo {
    private final String text;

    public Todo(String text) {
        Assert.notNull(text, () -> "text == null");
        Assert.isTrue(!text.isBlank(), () -> "text is blank");
        this.text = text;
    }

    @SuppressWarnings("unused")
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
