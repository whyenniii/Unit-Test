package wisoft.io.password.chap04.exercise;

public class FakeComplicatedLogger implements IComplicatedLogger {
    public String info = "";
    public String debug = "";
    public String error = "";
    public String warn = "";
    @Override
    public void info(final String text) {
        this.info = text;
    }

    @Override
    public void debug(final String text, final String obj) {
        this.debug = text;
    }

    @Override
    public void error(final String text) {
        this.error = text;
    }

    @Override
    public void warn(final String text, final String location, final String stacktrace) {
        this.warn = text;
    }
}
