package wisoft.io.password.chap04.constructorInterface.interfaces;

public interface IComplicatedLogger {
    void info (String text);
    void debug (String text, String object);
    void warn (String text);
    void error (String text, String location, String stacktrace);
}
