package wisoft.io.password;

import wisoft.io.time.RealTimeProvider;

import java.util.List;

public class VerifierFactory {
    public static VerifierPasswordInjection passwordVerifierFactory(List<Rule> rules) {
        return new VerifierPasswordInjection(rules, new RealTimeProvider());
    }
}