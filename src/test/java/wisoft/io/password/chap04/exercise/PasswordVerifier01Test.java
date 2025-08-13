package wisoft.io.password.chap04.exercise;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wisoft.io.password.chap04.exercise.FakeLogger;

public class PasswordVerifier01Test {
    @Test
    @DisplayName("password verifier with interfaces")
    void verityWithLoggerCallsLogger() {
        FakeLogger mockLog = new FakeLogger();
        PasswordVerifier01 verifier = new PasswordVerifier01(List.of(), mockLog);

        verifier.verify("any input");
        assertTrue(mockLog.written.contains("PASSED"));
    }
}
