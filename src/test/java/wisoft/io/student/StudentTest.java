//package wisioft.io;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DisplayName("학생 클래스 테스트")
//class StudentTest {
//
//    @Test
//    @DisplayName("기본 생성, 삭제 테스트")
//    void BasicTest() {
//        Student student = new Student("20221038", "임예은");
//
//        assertEquals("20221038", student.getId());
//        assertEquals("임예은", student.getName());
//        assertFalse(student.isDeleted());
//
//        student.delete();
//        assertTrue(student.isDeleted());
//    }
//
//    @ParameterizedTest(name = "학번 : {0}, 이름 : {1}")
//    @CsvSource({
//            "20221234, 임예은",
//            "20221999, 신보연",
//            "20223728, 이선혜"
//    })
//    @DisplayName("다양한 입력값을 받는 테스트")
//    void VariousTest(String id, String name) {
//        Student student = new Student(id, name);
//
//        assertEquals(id, student.getId());
//        assertEquals(name, student.getName());
////        assertFalse(student.isDeleted());
//
//        student.delete();
//        assertTrue(student.isDeleted());
//    }
//}
