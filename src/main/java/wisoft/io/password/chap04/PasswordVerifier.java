package wisoft.io.password.chap04;

import java.util.List;
import java.util.function.Function;

public class PasswordVerifier {

    //예제 4-1 복잡한 로거 함수에 직접적으로 의존하기
    public boolean verifyPassword(String input, List<Function<String, Boolean>> rules) {
        long failedCount = rules.stream()
                .map(rule -> rule.apply(input))
                .filter(result -> result == false)
                .count();

        if (failedCount == 0) {
            ComplicatedLogger.info("PASSED");
            return true;
        } else {
            ComplicatedLogger.info("FAIL");
            return false;
        }
    }

    //예제 4-2 가짜 로거 함수를 매개변수로 주입하기
    public static boolean verifyPassword2(String input, List<Function<String, Boolean>> rules, Logger logger) {
        long failedCount = rules.stream()
                .map(rule -> rule.apply(input))
                .filter(result -> !result)
                .count();

        if (failedCount == 0) {
            logger.info("PASSED");
            return true;
        }

        logger.info("FAIL");
        return false;
    }

}
