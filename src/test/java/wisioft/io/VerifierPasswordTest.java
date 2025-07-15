package wisioft.io;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.TestExecutionListeners;

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
}