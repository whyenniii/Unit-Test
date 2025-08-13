package wisoft.io.password.chap04.constructorInterface;

import java.util.List;
import wisoft.io.password.Rule;
import wisoft.io.password.chap04.constructorInterface.interfaces.IComplicatedLogger;

public class PasswordVerifier01 {
    private final List<Rule> rules;
    private final IComplicatedLogger logger;

    public PasswordVerifier01(List<Rule> rules, IComplicatedLogger logger){
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
