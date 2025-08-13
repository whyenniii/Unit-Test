package wisoft.io.password.chap04.constructorInterface;

import java.util.List;
import wisoft.io.password.Rule;

public class PasswordVerifier {
    private final List<Rule> rules;
    private final ILogger logger;

    public PasswordVerifier(final List<Rule> rules, final ILogger logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input) {
        long failed = rules.stream()
                .map(rule -> rule.apply(input))
                .filter(result -> result.passed() == false)
                .count();
        if (failed == 0) {
            logger.info("PASSED");
            return true;
        }
        logger.info("FAIL");
        return false;
    }
}
