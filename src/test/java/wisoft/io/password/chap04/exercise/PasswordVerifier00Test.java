package wisoft.io.password.chap04.exercise;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wisoft.io.password.chap04.FakeLogger;
import wisoft.io.password.chap04.Logger;

public class PasswordVerifier00Test {

    @Test
    @DisplayName("duck typing with function constructor injection")
    void loggerPassingScenarioCallsLoggerWithPassed() {
        final String[] logged = {""};
        Logger mockLog = message -> logged[0] = message;
        PasswordVerifier00 verifier = new PasswordVerifier00(List.of(), mockLog);

        verifier.verify("any input");
        assertTrue(logged[0].contains("PASSED"));
    }

    @Test
    @DisplayName("fake Logger Test")
    void loggerScenarioCallsLoggerWithPassed() {
        FakeLogger fakeLogger = new FakeLogger();
        PasswordVerifier00 verifier = new PasswordVerifier00(List.of(), fakeLogger);
        verifier.verify("any input");
        assertTrue(fakeLogger.logged.contains("PASSED"));
    }
}
