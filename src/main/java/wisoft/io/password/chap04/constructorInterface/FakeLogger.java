package wisoft.io.password.chap04.constructorInterface;

public class FakeLogger implements ILogger {
    public String written = "";

    @Override
    public void info(final String text) {
        this.written = text;
    }
}
