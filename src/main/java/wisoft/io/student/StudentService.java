package wisoft.io.student;

import java.util.HashMap;
import java.util.Map;

public class StudentService {

    private final Map<String, Student> studentMap = new HashMap<>();

    public StudentResult create(String studentId, String name) {
        //Validate 한번에 합쳤엉
        if (studentId == null || studentId.trim().isEmpty()) {
            return StudentResult.fail("학번은 비어 있을 수 없습니다.");
        }

        if (name == null || name.trim().isEmpty()) {
            return StudentResult.fail("이름은 비어 있을 수 없습니다.");
        }

        if (!studentId.matches("\\d+")) {
            return StudentResult.fail("학번은 숫자만 가능합니다.");
        }

        if (find(studentId) != null) {
            return StudentResult.fail("이미 존재하는 학번입니다.");
        }

        studentMap.put(studentId, new Student(studentId, name));
        return StudentResult.ok();
    }

    public StudentResult delete(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return StudentResult.fail("학번은 비어 있을 수 없습니다.");
        }

        if (find(studentId) == null) {
            return StudentResult.fail("삭제 요청하는 학번이 존재하지 않습니다.");
        }

        studentMap.remove(studentId);
        return StudentResult.ok();
    }

    public Student find(String studentId) {
        return studentMap.get(studentId);
    }
}