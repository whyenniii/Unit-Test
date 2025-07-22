package wisoft.io.password;

import wisoft.io.time.RealTimeProvider;

import java.util.List;

public class VerifierFactory {
    public static VerifierPasswordInjection createVerifier(List<Rule> rules) {
        return new VerifierPasswordInjection(rules, new RealTimeProvider());
    }
}