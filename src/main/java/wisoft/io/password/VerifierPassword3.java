package wisoft.io.password;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class VerifierPassword3 {
    public List<String> verifyPassword3(String input, List<Rule> rules, Supplier<DayOfWeek> getDayFn) {
        DayOfWeek dayOfWeek = getDayFn.get();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            throw new RuntimeException("주말이다옹옹..");
        }

        //다른 검증 로직
        return List.of();
    }
}
