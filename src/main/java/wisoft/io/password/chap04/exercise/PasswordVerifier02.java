package wisoft.io.password.chap04.exercise;

import java.util.List;
import wisoft.io.password.Rule;

public class PasswordVerifier02 {
    private List<Rule> rules;
    private IComplicatedLogger logger;

    public PasswordVerifier02(final List<Rule> rules, final IComplicatedLogger logger){
        this.rules = rules;
        this.logger = logger;
    }

    boolean verify(String input) {
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
