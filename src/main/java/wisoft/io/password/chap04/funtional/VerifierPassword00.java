package wisoft.io.password.chap04.funtional;
import java.util.List;
import java.util.function.Predicate;

public class VerifierPassword00 {

    @FunctionalInterface
    public interface Logger {
        void info(String text);
    }

    @FunctionalInterface
    public interface PasswordChecker {
        boolean verify(String input);
    }

    //커링 스타일: 규칙과 로거 먼저 주입 -> 나중에 비밀번호를 넘겨서 검사
    public static PasswordChecker verifyPassword3 (List<Predicate<String>> rules, Logger logger) {
        return (input) -> {
            long failed = rules.stream()
                    .map(rule -> rule.test(input))
                    .filter(result -> !result)
                    .count();

            if (failed == 0) {
                logger.info("PASSED");
                return true;
            } else {
                logger.info("FAILED");
                return false;
            }
        };
    }
}
