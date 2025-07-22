package wisoft.io.password;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class VerifierPassword2 {
    public List<String> verify(String input, List<Rule> rules, DayOfWeek currentDay) {
        if (currentDay == DayOfWeek.SATURDAY || currentDay == DayOfWeek.SUNDAY) {
            throw new RuntimeException("주말이다옹..");
        }

        List<String> errors = new ArrayList<>();
        for (Rule rule : rules) {
            VerifyResult result = rule.apply(input);
            if (!result.passed()) {
                errors.add(result.reason());
            }
        }

        return errors;
    }
}