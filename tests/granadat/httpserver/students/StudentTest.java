package granadat.httpserver.students;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void test_student_tostring_representation() throws Exception {
        Student student = new Student(1, "Doe", "John", "INFO");
        assertEquals("1 Doe John : INFO", student.toString());
    }

}