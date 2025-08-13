package wisoft.io.password.chap04.constructorInterface;

public class SimpleLogger implements ILogger {
    @Override
    public void info(final String text) {
        //로그 처리를 로직
        System.out.println(text);
    }
}
