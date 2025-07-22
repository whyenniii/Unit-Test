package wisoft.io.password;

import java.util.ArrayList;
import java.util.List;

public class VerifierPassword {

    private static List<Rule> rules = new ArrayList<>();

    public void addRules(Rule rule) {
        rules.add(rule);
    }

    public List<String> verify(String input) {
        List<String> errors = new ArrayList<>();

        if (rules.isEmpty()) {
            throw new IllegalArgumentException("no rules");
        }

        for (Rule rule : rules) {
            VerifyResult result = rule.apply(input);
            if (result.passed() == false) {
                errors.add(result.reason());
            }
        }

        return errors;
    }
}
