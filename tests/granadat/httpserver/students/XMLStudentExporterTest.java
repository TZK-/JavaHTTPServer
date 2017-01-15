package granadat.httpserver.students;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;


public class XMLStudentExporterTest {

    @Test
    public void test_export_students_to_xml_file() throws Exception {
        StudentsManager studentsManagerBeforeExport = new StudentsManager(new XMLStudentLoader(), StudentsManagerTest.STUDENT_XML_LOAD);

        Exportable exportable = new XMLStudentExporter(studentsManagerBeforeExport);

        String exportPath = "students_test_export.xml";

        exportable.export(exportPath);
        assertTrue(new File(exportPath).exists());

        StudentsManager studentsManagerAfterExport = new StudentsManager(new XMLStudentLoader(), exportPath);

        assertEquals(studentsManagerBeforeExport.toString(), studentsManagerAfterExport.toString());

    }

}