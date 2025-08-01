package wisoft.io.password;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import wisoft.io.time.TimeProvider;

public class VerifierPasswordInjection {
    //3.6 생성자 주입
//    private final List<Rule> rules;
//    private final DayOfWeek dayOfWeekFn;
//
//    VerifierPasswordInjection(List<Rule> rules, DayOfWeek dayOfWeekFn) {
//        this.rules = rules;
//        this.dayOfWeekFn = dayOfWeekFn;
//    }
//
//    public List<String> verify(String input) {
//        List<String> errors = List.of();
//        if(dayOfWeekFn == DayOfWeek.SATURDAY || dayOfWeekFn == DayOfWeek.SUNDAY) {
//            throw new RuntimeException("It's the weekend!");
//        }
//
//        return errors;
//    }

    //3-7 함수 대신 객체 주입
    private final List<Rule> rules;
    private final TimeProvider timeProvider;

    public VerifierPasswordInjection(List<Rule> rules, TimeProvider timeProvider) {
        this.rules = rules;
        this.timeProvider = timeProvider;
    }

    public List<String> verify(String input) {
        List<String> errors = new ArrayList<>();
        DayOfWeek day = timeProvider.getDay();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            throw new RuntimeException("It's the weekend!");
        }

        for (Rule rule: rules) {
            VerifyResult result = rule.apply(input);
            if (result.passed() == false) {
                errors.add(result.reason());
            }
        }

        return errors;
    }
}