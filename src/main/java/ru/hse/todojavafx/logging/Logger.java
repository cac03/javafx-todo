package ru.hse.todojavafx.logging;

import ru.hse.todojavafx.util.Assert;

import java.time.Instant;

public final class Logger {
    private final String tag;

    private Logger(String tag) {
        this.tag = tag;
    }

    public static Logger forClass(Class<?> clazz) {
        Assert.notNull(clazz, () -> "clazz == null");
        return new Logger(clazz.getSimpleName());
    }

    public void trace(String message, Object... arguments) {
        System.out.println(tag + " " + Instant.now() + " [TRACE] " + String.format(message, arguments));
    }

    public void debug(String message, Object... arguments) {
        System.out.println(tag + " " + Instant.now() + " [DEBUG] " + String.format(message, arguments));
    }

    public void info(String message, Object... arguments) {
        System.out.println(tag + " " + Instant.now() + " [INFO] " + String.format(message, arguments));
    }

    public void warn(Throwable throwable, String message, Object... arguments) {
        System.out.println(tag + " " + Instant.now() + " [WARN] " + String.format(message, arguments));
        if (throwable != null) {
            throwable.printStackTrace(System.out);
        }
    }

    public void warn(String message, Object... arguments) {
        warn(null, message, arguments);
    }

    public void error(Throwable throwable, String message, Object... arguments) {
        System.out.println(tag + " " + Instant.now() + " [ERROR] " + String.format(message, arguments));
        if (throwable != null) {
            throwable.printStackTrace(System.out);
        }
    }

    public void error(String message, Object... arguments) {
        error(null, message, arguments);
    }
}
