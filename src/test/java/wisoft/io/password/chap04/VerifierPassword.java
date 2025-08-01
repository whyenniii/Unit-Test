package wisoft.io.password.chap04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerifierPassword {

    @Test
    @DisplayName("when all rules pass, class logger with passed")
    void whenAllRulesPassCallsLoggerWithPassed() {

        class MockLogger implements Logger {
            String written = "";

            @Override
            public void info(String message) {
                this.written = message;
            }
        }

        MockLogger mockLogger = new MockLogger();

        Function<String, Boolean> alwaysPass = input -> true;

        PasswordVerifier.verifyPassword2("anything", List.of(alwaysPass), mockLogger);

        assertEquals("PASSED", mockLogger.written);
    }
}
