package ru.hse.todojavafx;

import javafx.application.Application;

/**
 * На занятии {@code main} метод находился в классе {@link TodoApplication}.
 * Оказалось, что если он не в классе, наследующем {@link Application}, то никакой {@code module-info.java}
 * добавлять не нужно
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(TodoApplication.class, args);
    }
}
