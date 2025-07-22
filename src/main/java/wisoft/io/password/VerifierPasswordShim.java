package wisoft.io.password;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

//public class VerifierPasswordShim {
//
//    //기본 의존성
//    private static final Supplier<DayOfWeek> originalDayOfWeekSupplier = () -> LocalDate.now().getDayOfWeek();
//
//    //현재 의존성
//    private static Supplier<DayOfWeek> dayOfWeekSupplier = originalDayOfWeekSupplier;
//
//    //의존성 주입 fake
//    public static Runnable inject(Supplier<DayOfWeek> fakeSupplier) {
//        dayOfWeekSupplier = fakeSupplier;
//
//        //reset 함수
//        return () -> dayOfWeekSupplier = originalDayOfWeekSupplier;
//    }

//    public static List<String> verifyPassword(String input, List<Rule> rules) {
//        DayOfWeek today = dayOfWeekSupplier.get();
//        if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY) {
//            throw new RuntimeException("It's the weekend!");
//        }
//
//        List<String> errors = new ArrayList<>();
//        for (Rule rule : rules) {
//            VerifyResult result = rule.apply(input);
//            if (!result.passed()) {
//                errors.add(result.reason());
//            }
//        }
//
//        return errors;
//    }
//}

// 실제 의존성 (LocalDate.now().getDayOfWeek())를 기본값으로 갖는 전역 supplier
public class VerifierPasswordShim {
    private static final Supplier<DayOfWeek> originalDayOfWeekSupplier =
            () -> LocalDate.now().getDayOfWeek();

    private static Supplier<DayOfWeek> dayOfWeekSupplier = originalDayOfWeekSupplier;

    public static Runnable inject(Supplier<DayOfWeek> fakeSupplier) {
        dayOfWeekSupplier = fakeSupplier;
        return () -> dayOfWeekSupplier = originalDayOfWeekSupplier;
    }

    public static List<String> verifyPassword(String input) {
        DayOfWeek today = dayOfWeekSupplier.get();
        if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY) {
            throw new RuntimeException("It's the weekend!");
        }

        return List.of();
    }
}