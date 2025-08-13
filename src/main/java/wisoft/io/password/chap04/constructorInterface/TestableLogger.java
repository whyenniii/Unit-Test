package wisoft.io.password.chap04.constructorInterface;

import wisoft.io.password.chap04.Logger;

public class TestableLogger implements Logger {
    String logged = "";
    @Override
    public void info(final String text) {
        this.logged = text;
    }
}
