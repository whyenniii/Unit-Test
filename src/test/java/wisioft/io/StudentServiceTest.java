package wisioft.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학생 추가 인증 테스트")
public class StudentServiceTest {
    StudentService service;

    @BeforeEach
    public void setUp() {
        service = new StudentService();
    }

    @Test
    @DisplayName("성공적으로 학생 생성")
    void createTest() {
        StudentResult result = service.create("202501", "보연");

        assertTrue(result.success());
        assertEquals("", result.reason());

        Student student = service.find("202501");
        assertNotNull(student);
        assertEquals("보연", student.getName());
    }

    @Test
    @DisplayName("다양한 입력값을 받는 테스트")
    void VariousTest() {
        String[][] inputs = {
                {"20221234", "임예은"},
                {"20221999", "신보연"},
                {"20223728", "이선혜"}
        };

        for (String[] input : inputs) {
            String id = input[0];
            String name = input[1];

            StudentResult result = service.create(id, name);
            assertTrue(result.success());
            assertEquals("", result.reason());

            Student student = service.find(id);
            assertNotNull(student);
            assertEquals(name, student.getName());
        }
    }

    @Test
    @DisplayName("학번이 null이면 예외 발생")
    void test02() {
        StudentResult result = service.create(null, "보연");

        assertFalse(result.success());
        assertEquals("학번은 비어 있을 수 없습니다.", result.reason());
    }

    @Test
    @DisplayName("이름이 null이면 예외가 발생")
    void test03() {
        StudentResult result = service.create("202501", null);

        assertFalse(result.success());
        assertEquals("이름은 비어 있을 수 없습니다.", result.reason());
    }

    @Test
    @DisplayName("학번이 숫자가 아니면 예외가 발생")
    void test04() {
        StudentResult result = service.create("abc123", "보연");

        assertFalse(result.success());
        assertEquals("학번은 숫자만 가능합니다.", result.reason());
    }

    @Test
    @DisplayName("중복된 학번이면 예외 발생")
    void test05() {
        service.create("202501", "보연");
        StudentResult result = service.create("202501", "다른이름");

        assertFalse(result.success());
        assertEquals("이미 존재하는 학번입니다.", result.reason());
    }

    @Test
    @DisplayName("삭제 요청 학번이 null이면 예외가 발생")
    void test06() {
        StudentResult result = service.delete(null);

        assertFalse(result.success());
        assertEquals("학번은 비어 있을 수 없습니다.", result.reason());
    }

    @Test
    @DisplayName("존재하지 않는 학번 삭제시 예외가 발생")
    void test07() {
        StudentResult result = service.delete("9999");

        assertFalse(result.success());
        assertEquals("삭제 요청하는 학번이 존재하지 않습니다.", result.reason());
    }

    @Test
    @DisplayName("학생 정상 삭제 테스트")
    void test08() {
        service.create("202501", "보연");

        StudentResult result = service.delete("202501");

        assertTrue(result.success());
        assertEquals("", result.reason());

        assertNull(service.find("202501"));
    }
}
