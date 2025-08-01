package wisoft.io.password;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import wisoft.io.time.FakeTimeProvider;

@DisplayName("verifyPassword")
class VerifierPasswordTest {

    private VerifierPassword makeVerifier() {
        return new VerifierPassword();
    }

    private Rule passingRule() {
        return input -> new VerifyResult(true, "");
    }

    private VerifyResult oneUpperCase(String input) {
        return new VerifyResult((input.toLowerCase() != input), "at least one upper case needed");
    }

    private VerifierPassword makeFailingRule(String reason) {
        VerifierPassword verifier = makeVerifier();
        Rule fakeRuleFail =  input -> new VerifyResult(false, reason);
        verifier.addRules(fakeRuleFail);
        return verifier;
    }
    private VerifierPassword makePassingRule() {
        VerifierPassword verifier = makeVerifier();
        verifier.addRules(passingRule());
        return verifier;
    }


    @Nested
    @DisplayName("with a failing rule")
    class FailingRule {
        @Test
        @DisplayName("has an error message based on the rule.reason")
        void hasErrorMassageBasedRuleReason() {
            VerifierPassword verifier = makeFailingRule("fake reason");
            List<String> errors = verifier.verify("any value");
            assertThat(errors.get(0)).contains("fake reason");
        }

        @Test
        @DisplayName("has exactly one error")
        void hasExactlyOneError() {
            VerifierPassword verifier = makeFailingRule("fake reason");
            List<String> errors = verifier.verify("any value");
            assertEquals(1, errors.size());
        }
    }

    @Nested
    @DisplayName("with a passing rule")
    class PassingRule {

        @Test
        @DisplayName("has no errors")
        void hasNoErrors() {
            VerifierPassword verifier = makePassingRule();
            List<String> errors = verifier.verify("any value");
            assertEquals(0, errors.size());
        }
    }

    @Nested
    @DisplayName("with a failing and a passing rule")
    class MixedRule {

        @Test
        @DisplayName("has one error")
        void hasOneError() {
            VerifierPassword verifier = makeFailingRule("fake reason");
            verifier.addRules(passingRule());
            List<String> errors = verifier.verify("any value");
            assertEquals(1, errors.size());
        }

        @Test
        @DisplayName("error text belongs to failed rule")
        void errorBelongsToFailedRule() {
            VerifierPassword verifier = makeFailingRule("fake reason");
            verifier.addRules(passingRule());
            List<String> errors = verifier.verify("any value");
            assertTrue(errors.get(0).contains("fake reason"));
        }

        @Test
        @DisplayName("verify, with no rules, throws exception")
        void errorWithNoRuleException() {
            VerifierPassword verifier = makeVerifier();

            assertThatThrownBy(() -> verifier.verify("any input")).isInstanceOf(IllegalArgumentException.class).hasMessage("no rules");

        }
    }

    @Nested
    @DisplayName("one uppercase rule")
    class oneUppercaseRule {

        @Test
        @DisplayName("given no uppercase, it fails")
        void givenNoUppercase() {
            VerifyResult result = oneUpperCase("abc");
            assertFalse(result.passed());
        }

        @ParameterizedTest
        @CsvSource({
                "Abc",
                "aBc"
        })
        @DisplayName("given one uppercase, it passes")
        void givenOneUppercase(String input) {
            VerifyResult result = oneUpperCase(input);
            assertTrue(result.passed());
        }

        @ParameterizedTest
        @CsvSource({
                "Abc, true",
                "aBc, true",
                "abc, false"
            })
        @DisplayName("one uppercase rule")
        void oneUppercaseRule(String input, boolean expected) {
            VerifyResult result = oneUpperCase(input);
            assertEquals(expected, result.passed());
        }

//        @Test
//        @DisplayName("given one uppercase, it passes")
//        void givenOneUppercase() {
//            VerifyResult result = oneUpperCase("Abc");
//            assertTrue(result.passed());
//        }
//
//        @Test
//        @DisplayName("given different uppercase, it passes")
//        void givenDifferentUppercase() {
//            VerifyResult result = oneUpperCase("aBc");
//            assertTrue(result.passed());
//        }

    }

    @Nested
    @DisplayName("time00 test")
    class Time00Test {
        private final DayOfWeek today = LocalDate.now().getDayOfWeek();

        @Test
        @DisplayName("when its the weekend, throws an error")
        void whenWeekendsThrowsException() {
            if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY) {
                VerifierPassword verifier = makeVerifier();
                Exception e = assertThrows(RuntimeException.class, () -> {
                    verifier.verify("any input");
                });

                assertEquals("주말이다옹..", e.getMessage());
            }
        }

        @Test
        @DisplayName("on weekends, throws exceptions")
        void onAWeekend_throwsAnError() {
            assumeTrue(today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY);
            VerifierPassword verify = makeVerifier();
            Exception exception = assertThrows(RuntimeException.class, () -> {
                verify.verify("any input");
            });
            assertEquals("주말이다옹..", exception.getMessage());
            }
        }
    }

    @Nested
    @DisplayName("verifier2 - dummy object")
    class VerifierPassword2Test {

        private final VerifierPassword2 verifier = new VerifierPassword2();

        @Test
        @DisplayName("on weekends, throws exceptions")
        void onWeekends_throwsExceptions() {
            assertThatThrownBy(() ->
                    verifier.verify("anything", List.of(), DayOfWeek.SUNDAY)
            ).isInstanceOf(RuntimeException.class)
                    .hasMessage("주말이다옹..");

            assertThatThrownBy(() ->
                    verifier.verify("anything", List.of(), DayOfWeek.SATURDAY)
            ).isInstanceOf(RuntimeException.class)
                    .hasMessage("주말이다옹..");
        }
    }

    @Nested
    @DisplayName("verifier3 - dummy function")
    class VerifierPassword3Test {

        private final VerifierPassword3 verifier = new VerifierPassword3();

        @Test
        @DisplayName("on weekends, throws exceptions")
        void throwsExceptionOnWeekend() {
            // 자바에서 함수 객체처럼 Supplier 사용
            Supplier<DayOfWeek> alwaysSunday = () -> DayOfWeek.SUNDAY;

            assertThatThrownBy(() ->
                    verifier.verifyPassword3("anything", List.of(), alwaysSunday)
            ).isInstanceOf(RuntimeException.class)
                    .hasMessage("주말이다옹..");
        }
    }

//    @Nested
//    @DisplayName("verifier factory")
//    class VerifierFactoryTest {
//
//        @Test
//        @DisplayName("factory method: on weekends, throws exceptions")
//        void throwsExceptionOnWeekend() {
//            // Arrange
//            Supplier<DayOfWeek> alwaysSunday = () -> DayOfWeek.SUNDAY;
//            Function<String, List<String>> verifyPassword =
//                    VerifierFactory.makeVerifier(List.of(), alwaysSunday);
//
//            // Act + Assert
//            assertThatThrownBy(() -> verifyPassword.apply("anything"))
//                    .isInstanceOf(RuntimeException.class)
//                    .hasMessage("It's the weekend!");
//        }
//    }

    @Nested
    @DisplayName("inject test")
    class PasswordVerifierShimTest {

//        @Test
//        void throwsOnWeekend_usingInjectedDay() {
//            Runnable reset = VerifierPasswordShim.inject(() -> DayOfWeek.SUNDAY);
//
//            try {
//                assertThatThrownBy(() -> VerifierPasswordShim.verifyPassword("any input", List.of()))
//                        .isInstanceOf(RuntimeException.class)
//                        .hasMessage("It's the weekend!");
//            } finally {
//                //의존성 reset
//                reset.run();
//            }
//        }

        @Test
        @DisplayName("when it's the weekend, throws an error")
        void throwsErrorOnWeekend() {
            //SATURDAY
            Supplier<DayOfWeek> saturday = () -> DayOfWeek.SATURDAY;

            //주입하고 reset 핸들러 반환받기
            Runnable reset = VerifierPasswordShim.inject(saturday);

            try {
                assertThrows(RuntimeException.class, () ->
                        VerifierPasswordShim.verifyPassword("any input"), "It's the weekend!");
            } finally {
                //테스트 이후 복원
                reset.run();
            }
        }
    }


    //예제 3-10 생성자 주입 방식
//    @Nested
//    @DisplayName("class constructor")
//    class PasswordVerifier {
//        @Test
//        @DisplayName("on weekends, throws exception")
//        void onWeekendsThrowsException() {
//            DayOfWeek sunday = DayOfWeek.SUNDAY;
//
//            VerifierPasswordInjection verifier = new VerifierPasswordInjection(List.of(), sunday);
//
//            RuntimeException exception = assertThrows(RuntimeException.class, () -> verifier.verify("anything"));
//
//            assertEquals("It's the weekend!", exception.getMessage());
//        }
//
//        private VerifierPasswordInjection makeVerifier(List<Rule> rules, DayOfWeek dayFn) {
//            return new VerifierPasswordInjection(rules, dayFn);
//        }
//
//        @Test
//        @DisplayName("refactor with constructor")
//        void refactorWithConstructor() {
//            DayOfWeek sunday = DayOfWeek.SUNDAY;
//            VerifierPasswordInjection verifier = makeVerifier(List.of(), sunday);
//
//            RuntimeException exception = assertThrows(RuntimeException.class, () -> verifier.verify("anything"));
//
//            assertEquals("It's the weekend!", exception.getMessage());
//        }
//
//        @Test
//        @DisplayName("on weekdays, with no rules, passes")
//        void onWeekdaysWithNoRulesPassed() {
//            DayOfWeek monday = DayOfWeek.MONDAY;
//            VerifierPasswordInjection verifier = makeVerifier(List.of(), monday);
//
//            List<String> result = verifier.verify("anything");
//
//            assertEquals(0, result.size());
//
//        }
//    }

//    @Nested
//    @DisplayName("password verifier Injection")
//    class PasswordVerifierWithInjectionTest {
//
//        @Test
//        void throwsExceptionOnWeekend() {
//            Supplier<DayOfWeek> alwaysSunday = () -> DayOfWeek.SUNDAY;
//
//            VerifierPasswordInjection verifier = new VerifierPasswordInjection(List.of(), alwaysSunday);
//
//            RuntimeException exception = assertThrows(RuntimeException.class, () ->
//                    verifier.verify("anything"));
//
//            assertEquals("It's the weekend!", exception.getMessage());
//        }
//    }

    @Nested
    @DisplayName("password test")
    class VerifierPassword4Test {

        @Test
        @DisplayName("on weekends, throws exception")
        void onWeekendsThrowsException() {
            FakeTimeProvider fakeSunday = new FakeTimeProvider(DayOfWeek.SUNDAY);
            VerifierPasswordInjection verifier = new VerifierPasswordInjection(List.of(), fakeSunday);

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                verifier.verify("anything");
            });

            assertEquals("It's the weekend!", exception.getMessage());
        }
    }
