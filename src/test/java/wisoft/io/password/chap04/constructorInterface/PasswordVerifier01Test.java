package wisoft.io.password.chap04.constructorInterface;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wisoft.io.password.chap04.Logger;
import wisoft.io.password.chap04.constructor.PasswordVerifier00;
import wisoft.io.password.chap04.constructorInterface.interfaces.FakeComplicatedLogger;

public class PasswordVerifier01Test {
    @Test
    @DisplayName("working with long interfaces")
    void verifyPassingWithLoggerCallsLoggerWithPass() {
        FakeComplicatedLogger mockLog = new FakeComplicatedLogger();

        PasswordVerifier01 verifier = new PasswordVerifier01(List.of(), mockLog);
        verifier.verify("anything");

        assertTrue(mockLog.infoWritten.contains("PASSED"));
    }

    @Test
    @DisplayName("a more JS oriented variation on this test")
    void moreVariationTest() {
        String logged = "";
        FakeComplicatedLogger mockLog = new FakeComplicatedLogger();
        mockLog.info(logged);

        PasswordVerifier01 verifier = new PasswordVerifier01(List.of(), mockLog);
        verifier.verify("anything");

        assertTrue(mockLog.infoWritten.contains("PASSED"));

    }

    class TestableLogger implements Logger {

        String logged = "";
        public void info(final String text) {
            this.logged = text;
        }
    }

    @Test
    @DisplayName("partial mock with ingeritance")
    void verifyWithLoggerCallsLogger() {
        TestableLogger mockLog = new TestableLogger();

        PasswordVerifier00 verifier = new PasswordVerifier00(List.of(), mockLog);
        verifier.verify("any input");

        assertTrue(mockLog.logged.contains("PASSED"));
    }
}
