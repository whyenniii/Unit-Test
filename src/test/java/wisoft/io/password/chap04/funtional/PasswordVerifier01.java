package wisoft.io.password.chap04.funtional;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordVerifier01 {
    @Test
    @DisplayName("higher order factory functions")
    void givenLoggerAndPassingScenario() {
        final String[] logged = {""};
        Logger mockLogger = message -> logged[0] = message;

        Function<String, Boolean> passVerify =
                VerifierPassword01.makeVerifier(List.of(), mockLogger);

        passVerify.apply("any input");

        assertTrue(logged[0].contains("PASSED"));
    }
}
