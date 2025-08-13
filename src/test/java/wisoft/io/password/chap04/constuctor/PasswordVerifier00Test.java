package wisoft.io.password.chap04.constuctor;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.matcher.CollectionElementMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wisoft.io.password.chap04.FakeLogger;
import wisoft.io.password.chap04.constructor.PasswordVerifier00;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordVerifier00Test {
//    private String logged = "";

    @Test
    @DisplayName("duck typing with function constructor injection")
    void loggerPassingScenarioCallsLoggerWithPassed() {
//        Logger mockLogger = message -> logged = message;
//
//        PasswordVerifier00 verifier = new PasswordVerifier00(List.of(), mockLogger);
//        verifier.verify("any input");
//
//        assertTrue(logged.contains("PASSED"));
        FakeLogger fakeLogger = new FakeLogger();

        PasswordVerifier00 verifier = new PasswordVerifier00(List.of(), fakeLogger);
        verifier.verify("any input");

        assertTrue(fakeLogger.logged.contains("PASSED"));

    }
}

