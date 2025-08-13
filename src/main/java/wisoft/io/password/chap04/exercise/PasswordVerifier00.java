package wisoft.io.password.chap04.exercise;

import java.util.List;
import wisoft.io.password.Rule;
import wisoft.io.password.chap04.Logger;

public class PasswordVerifier00 {
    public List<Rule> rules;
    public Logger logger;

    public PasswordVerifier00(final List<Rule> rules, final Logger logger) {
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
