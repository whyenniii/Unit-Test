package wisoft.io.password.chap04.constructor;

import static java.lang.Boolean.FALSE;

import java.util.List;
import wisoft.io.password.Rule;
import wisoft.io.password.chap04.Logger;

public class PasswordVerifier00 {
    private final List<Rule> rules;
    private final Logger logger;

    public PasswordVerifier00(List<Rule> rules, Logger logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input) {
        long failed = rules.stream()
                .map(rule -> rule.apply(input))
                .filter(result -> result.equals(false))
                .count();

        if (failed == 0) {
            logger.info("PASSED");
            return true;
        }
        logger.info("FAIL");
        return false;
    }
}
