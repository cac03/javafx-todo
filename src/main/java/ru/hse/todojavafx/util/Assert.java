package ru.hse.todojavafx.util;

import java.util.function.Supplier;

/**
 * Класс для проверки предусловий.
 * <p>
 * Пример использования:
 * <pre>
 *     public class IntRange {
 *         private final int fromClosed;
 *         private final int toClosed;
 *
 *         public IntRange(int fromClosed, int toClosed) {
 *             Assert.isTrue(toClosed >= fromClosed, () -> "fromClosed must be <= toClosed,
 *                     fromClosed = " + fromClosed + ", toClosed = " + toClosed);
 *             this.fromClosed = fromClosed;
 *             this.toClosed = toClosed;
 *         }
 *     }
 * </pre>
 * <p>
 * Вдохновлён <a href="https://github.com/spring-projects/spring-framework/blob/master/spring-core/src/main/java/org/springframework/util/Assert.java">Assert.java</a>
 */
@SuppressWarnings({"BooleanParameter", "unused"})
public final class Assert {
    private Assert() {
    }

    /**
     * Проверить, что {@code o != null}.
     * Если {@code o == null}, то будет выброшен {@link IllegalArgumentException}.
     *
     * @param o который нужно проверить на {@code null}
     * @param messageSupplier поставщик сообщения для исключения, может быть {@code null}
     * @throws IllegalArgumentException если {@code o == null}
     */
    public static void notNull(Object o, Supplier<String> messageSupplier) {
        if (o == null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Проверить, что {@code o != null}.
     * Если {@code o == null}, то будет выброшен {@link IllegalArgumentException}.
     *
     * @param o который нужно проверить на {@code null}
     * @param message сообщение для исключения, может быть {@code null}
     * @throws IllegalArgumentException если {@code o == null}
     */
    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Проверить условие, выраженное через boolean значение.
     * Если условие - {@code false}, то будет выброшен {@link IllegalArgumentException}
     * <p>
     * Пример:
     * <pre>
     *     Assert.isTrue(1 < 2, () -> "Arithmetic is broken");
     * </pre>
     *
     * @param condition для проверки
     * @param messageSupplier поставщик сообщения для исключения
     * @throws IllegalArgumentException если {@code condition == true}
     */
    public static void isTrue(boolean condition, Supplier<String> messageSupplier) {
        if (!condition) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Проверить состояние, выраженное через значение boolean значение.
     * Если оно - {@code false}, то будет выброшен {@link IllegalStateException}
     *
     * @param state для проверки
     * @param messageSupplier поставщик сообщения для исключения, может быть {@code null}
     * @throws IllegalStateException если {@code state == false}
     */
    public static void state(boolean state, Supplier<String> messageSupplier) {
        if (!state) {
            throw new IllegalStateException(nullSafeGet(messageSupplier));
        }
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        if (messageSupplier == null) {
            return null;
        }
        return messageSupplier.get();
    }
}
