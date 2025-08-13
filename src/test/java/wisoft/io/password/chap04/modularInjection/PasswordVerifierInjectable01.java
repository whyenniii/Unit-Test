package wisoft.io.password.chap04.modularInjection;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import wisoft.io.password.chap04.Logger;
import wisoft.io.password.chap04.PasswordVerifier;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordVerifierInjectable01 {

    private final String[] logged = {""};
    private final Logger mockLogger = new Logger() {
        @Override
        public void info(String message) {
            logged[0] = message;
        }
    };

    final PasswordVerifier verifier = new PasswordVerifier(mockLogger);

    @AfterEach
    void resetLogger() {
        // 원래 logger로 되돌리는 동작 (테스트 격리 유지)
        verifier.resetDependencies(mockLogger);
    }

    @Test
    void callsLoggerWithPassWhenNoRulesFail() {
        // 가짜 로거 주입
        verifier.injectDependencies(mockLogger);

        // 규칙 모두 통과하는 경우
        verifier.verifyPassword("anything", List.of());

        assertTrue(logged[0].contains("PASSWORD"));
    }
}
