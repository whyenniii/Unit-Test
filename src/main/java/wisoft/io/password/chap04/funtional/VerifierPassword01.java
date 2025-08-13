package wisoft.io.password.chap04.funtional;

import java.util.List;
import java.util.function.Function;
import wisoft.io.password.Rule;

@FunctionalInterface
interface Logger {
    void info(String message);
}

public class VerifierPassword01 {
    public static Function<String, Boolean> makeVerifier(
            List<Rule> rules,
            Logger logger
    ) {
        return (input) -> {
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
        };
    }
}

