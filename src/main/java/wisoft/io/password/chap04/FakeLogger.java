package wisoft.io.password.chap04;

public class FakeLogger implements Logger {
    public String logged = "";

    @Override
    public void info(final String message) {
        this.logged = message;
    }
}
