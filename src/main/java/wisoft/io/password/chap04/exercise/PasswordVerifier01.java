package wisoft.io.password.chap04.exercise;

import java.util.List;
import wisoft.io.password.Rule;
import wisoft.io.password.chap04.Logger;

public class PasswordVerifier01 {
    private List<Rule> rules;
    private ILogger logger;

    public PasswordVerifier01(final List<Rule> rules, final ILogger logger) {
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
