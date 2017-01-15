package granadat.httpserver.students;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentsManagerTest {

    private StudentsManager studentsManager;

    public static String STUDENT_XML_LOAD = "students_test.xml";
    private static String STUDENT_XML_EXPORT = "students_export_test.xml";

    @Before
    public void setUp() throws Exception {
        studentsManager = new StudentsManager(new XMLStudentLoader(), STUDENT_XML_LOAD);
    }

    @Test
    public void test_it_loads_students_data_from_xml_file() {
        assertEquals(4, studentsManager.getStudents().size());
    }

    @Test
    public void test_get_student_by_id_returns_the_right_one() {
        Student s1 = new Student(1, "Doe", "John", "CASIR");
        studentsManager.addStudent(s1);

        assertEquals(s1, studentsManager.getStudentById(1));
    }

    @Test
    public void test_add_student_inserts_into_map() {
        Student s1 = new Student(1, "Doe", "John", "CASIR");
        studentsManager.addStudent(s1);

        assertTrue(studentsManager.getStudents().containsValue(s1));
    }

    @Test
    public void test_tostring_representation() {
        String expected = "";
        for(Student student: studentsManager.getStudents().values()) {
            expected += student.toString();
            expected += System.getProperty("line.separator");
        }

        assertEquals(expected, studentsManager.toString());
    }

}
