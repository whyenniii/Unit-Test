package wisoft.io.password.chap04.exercise;

public class SimpleLogger implements ILogger {
    @Override
    public void info(final String text) {
        //로그처리 로직
        System.out.println(text);
    }
}
