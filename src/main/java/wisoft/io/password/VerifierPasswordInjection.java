package wisoft.io.password;

import java.time.DayOfWeek;
import java.util.List;
import wisoft.io.time.TimeProvider;

public class VerifierPasswordInjection {
    private final List<Rule> rules;
    private final TimeProvider timeProvider;

    public VerifierPasswordInjection(List<Rule> rules, TimeProvider timeProvider) {
        this.rules = rules;
        this.timeProvider = timeProvider;
    }

    public List<String> verify(String input) {
        DayOfWeek day = timeProvider.getDay();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            throw new RuntimeException("It's the weekend!");
        }

        //추가 검증 로직

        return List.of();
    }
}