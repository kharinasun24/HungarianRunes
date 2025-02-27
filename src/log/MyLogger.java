package log;

import java.time.format.DateTimeFormatter;

public class MyLogger {

    private String className;
    private LogLevel level;

    public MyLogger(Class<?> clazz) {
        this.className = clazz.getName();
        this.level = LogLevel.INFO; // Standardmäßig auf INFO setzen
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    // Weitere Log-Methoden für DEBUG, TRACE usw.

    private void log(LogLevel logLevel, String message) {
        if (logLevel.ordinal() >= level.ordinal()) {
            System.out.println(String.format("[%s] [%s] %s: %s",
                    logLevel,
                    java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]")),
                    className,
                    message));
        }
    }

    public enum LogLevel {
        TRACE, DEBUG, INFO, WARN, ERROR
    }
}