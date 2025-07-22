package wisoft.io.student;

public record StudentResult(boolean success, String reason) {
    public static StudentResult ok() {
        return new StudentResult(true, "");
    }

    public static StudentResult fail(String reason) {
        return new StudentResult(false, reason);
    }
}