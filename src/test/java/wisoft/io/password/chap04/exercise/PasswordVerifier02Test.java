package wisoft.io.password.chap04.exercise;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordVerifier02Test {


    @Test
    @DisplayName("verify passing, with logger, calls logger with passing")
    void fakeLoggerTest() {
        FakeComplicatedLogger mockLog = new FakeComplicatedLogger();
        PasswordVerifier02 verifier = new PasswordVerifier02(List.of(), mockLog);

        verifier.verify("any input");
        assertTrue(mockLog.info.contains("PASSED"));
    }

    @Test
    @DisplayName("A more JS oriented variation on this test")
    void moreVariationTest() {
        String logged = "";

        FakeComplicatedLogger mockLog  = new FakeComplicatedLogger();
        mockLog.info(logged);
        mockLog.debug(logged, logged);
        mockLog.error(logged);
        mockLog.warn(logged, logged, logged);

        PasswordVerifier02 verifier = new PasswordVerifier02(List.of(), mockLog);

        verifier.verify("any input");
        assertTrue(mockLog.info.contains("PASSED"));
    }
}
