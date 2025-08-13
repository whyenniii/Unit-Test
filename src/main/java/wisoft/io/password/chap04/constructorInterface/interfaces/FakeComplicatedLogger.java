package wisoft.io.password.chap04.constructorInterface.interfaces;

public class FakeComplicatedLogger implements IComplicatedLogger {
    public String infoWritten = "";
    public String debugWritten = "";
    public String errorWritten = "";
    public String warnWritten = "";

    @Override
    public void info(final String text) {
        this.infoWritten = text;
    }

    @Override
    public void debug(final String text, final String object) {
        this.debugWritten = text;
    }

    @Override
    public void warn(final String text) {
        this.warnWritten = text;
    }

    @Override
    public void error(final String text, final String location, final String stacktrace) {
        this.errorWritten = text;
    }
}
