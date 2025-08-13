package wisoft.io.password.chap04.funtional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import wisoft.io.password.chap04.funtional.VerifierPassword00;

class PasswordVerifier00{

    @Test
    void returnTrueAndLogsPassedWithWhenAllRulesPass() {
        final String[] logged = {""};

        VerifierPassword00.Logger logger = (msg) -> logged[0] += msg;

        List<Predicate<String>> rules = List.of();

        VerifierPassword00.PasswordChecker verifier = VerifierPassword00.verifyPassword3(rules, logger);

        boolean result = verifier.verify("anything");
        assertTrue(result);
        assertEquals("PASSED", logged[0]);
    }
}
