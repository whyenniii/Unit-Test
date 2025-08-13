package wisoft.io.password.chap04.exercise;

public interface IComplicatedLogger {
    void info(String text);
    void debug(String text, String obj);
    void error(String text);
    void warn(String text, String location, String stacktrace);
}
