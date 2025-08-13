package wisoft.io.password.chap04.exercise;

public class FakeLogger implements ILogger {
    public String written = "";
    @Override
    public void info(final String text) {
        this.written = text;
        System.out.println(text);
    }
}
