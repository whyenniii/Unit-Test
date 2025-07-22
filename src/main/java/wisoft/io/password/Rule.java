package wisoft.io.password;

public interface Rule {
    VerifyResult apply(String input);
}
