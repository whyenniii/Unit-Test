package wisoft.io.password.chap04;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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

    //예제 4-4 복잡한 모듈 의존성

    private ComplicatedLogger logger;

    public PasswordVerifier(ComplicatedLogger logger, ConfigurationService configService) {
        this.logger = logger;
        this.configService = configService;
    }

    private ConfigurationService configService;

    private void log(String text) {
        String level = configService.getLogLevel();
        if ("info".equalsIgnoreCase(level)) {
            logger.info(text);
        }
        if ("debug".equalsIgnoreCase(level)) {
            logger.debug(text);
        }
    }

    public boolean verifyPassword3(String input, List<Function<String, Boolean>> rules) {
        long failedCount = rules.stream()
                .map(rule -> rule.apply(input))
                .filter(result -> result.equals(Boolean.FALSE))
                .count();

        if (failedCount == 0) {
            log("PASSED"); // ➊
            return true;
        }
        log("FAIL"); // ➋
        return false;
    }

    //예제 4-5 모듈 주입 패턴으로 리팩터링하기
    private Logger logger2; // 의존성

    public PasswordVerifier(Logger logger2) {
        this.logger2 = logger2;
    }

    public void resetDependencies(Logger originalLogger) {
        this.logger2 = originalLogger;
    }

    public void injectDependencies(Logger fakeLogger) {
        this.logger2 = fakeLogger;
    }

    public boolean verifyPassword4(String input, List<Predicate<String>> rules) {
        long failedCount = rules.stream()
                .filter(rule -> !rule.test(input))
                .count();

        if (failedCount == 0) {
            logger.info("PASSED");
            return true;
        }

        logger.info("FAIL");
        return false;
    }
}
