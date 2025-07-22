package wisoft.io.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("student service test")
public class StudentServiceTest {

    private StudentService makeStudentService() {
        return new StudentService();
    }

    @Nested
    @DisplayName("success test")
    class SuccessTest {
        @Test
        @DisplayName("create test")
        void createTest() {
            StudentService studentService = makeStudentService();
            StudentResult result = studentService.create("202501", "보연");

            assertTrue(result.success());
            assertEquals("", result.reason());

            Student student = studentService.find("202501");
            assertNotNull(student);
            assertEquals("보연", student.getName());
        }

        @ParameterizedTest
        @CsvSource({
                "20221234, 임예은",
                "20221999, 신보연",
                "20223728, 이선혜"
        })
        @DisplayName("create test with various insert value ")
        void VariousTest(String id, String name) {
            StudentService studentService = makeStudentService();
            StudentResult result = studentService.create(id, name);
            assertTrue(result.success());
            assertEquals("", result.reason());

            Student student = studentService.find(id);
            assertNotNull(student);
            assertEquals(name, student.getName());
        }

        @Test
        @DisplayName("delete test")
        void test08() {
            StudentService studentService = makeStudentService();
            studentService.create("202501", "보연");

            StudentResult result = studentService.delete("202501");

            assertTrue(result.success());
            assertEquals("", result.reason());

            assertNull(studentService.find("202501"));
        }
    }

    @Nested
    @DisplayName("exception test")
    class ExceptionTest {
        @Test
        @DisplayName("create exception test if studentId is null")
        void test02() {
            StudentResult result = makeStudentService().create(null, "보연");

            assertFalse(result.success());
            assertEquals("학번은 비어 있을 수 없습니다.", result.reason());
        }

        @Test
        @DisplayName("create exception test if name is null")
        void test03() {
            StudentResult result = makeStudentService().create("202501", null);

            assertFalse(result.success());
            assertEquals("이름은 비어 있을 수 없습니다.", result.reason());
        }

        @Test
        @DisplayName("create exception test if studentId is not only number")
        void test04() {
            StudentResult result = makeStudentService().create("abc123", "보연");

            assertFalse(result.success());
            assertEquals("학번은 숫자만 가능합니다.", result.reason());
        }

        @Test
        @DisplayName("create exception test if studentId is already exist")
        void test05() {
            StudentService studentService = makeStudentService();
            studentService.create("202501", "보연");
            StudentResult result = studentService.create("202501", "다른이름");

            assertFalse(result.success());
            assertEquals("이미 존재하는 학번입니다.", result.reason());
        }

        @Test
        @DisplayName("delete exception test if studentId is null")
        void test06() {
            StudentResult result = makeStudentService().delete(null);

            assertFalse(result.success());
            assertEquals("학번은 비어 있을 수 없습니다.", result.reason());
        }

        @Test
        @DisplayName("delete exception test if studentId is not exist")
        void test07() {
            StudentResult result = makeStudentService().delete("9999");

            assertFalse(result.success());
            assertEquals("삭제 요청하는 학번이 존재하지 않습니다.", result.reason());
        }
    }
}
