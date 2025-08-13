package wisoft.io.password.chap04.constructorInterface;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordVerifier00Test {

    @Test
    @DisplayName("password verifier with interfaces")
    void verifyWithLoggerCallsLogger() {
        FakeLogger mockLog = new FakeLogger();

        PasswordVerifier verifier = new PasswordVerifier(List.of(), mockLog);
        verifier.verify("anything");

        assertTrue(mockLog.written.contains("PASSED"));

    }
}
