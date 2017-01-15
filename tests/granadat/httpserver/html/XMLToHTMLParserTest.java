package granadat.httpserver.html;

import granadat.httpserver.students.StudentsManager;
import granadat.httpserver.students.StudentsManagerTest;
import granadat.httpserver.students.XMLStudentLoader;
import org.junit.Test;

import static org.junit.Assert.*;


public class XMLToHTMLParserTest {

    @Test
    public void test_it_generates_right_html_from_students_manager() {
        StudentsManager studentsManager = new StudentsManager(new XMLStudentLoader(), StudentsManagerTest.STUDENT_XML_LOAD);
        XMLToHTMLParser xmlToHTMLParser = new XMLToHTMLParser(studentsManager);

        String expected = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\" lang=\"fr\">\n" +
                "    <head>\n" +
                "        <title>Students list</title>\n" +
                "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"/>\n" +
                "    </head>\n" +
                "    <body><table class=\"table table-striped\">\n" +
                "   <tr><th>Id</th>\n" +
                "<th>LastName</th>\n" +
                "<th>FirstName</th>\n" +
                "<th>Group</th>\n" +
                "<th>Action</th>\n" +
                "</tr><tr>\n" +
                "   <td>2</td>\n" +
                "   <td><a href=\"detail?id=2\">Paul</a></td>\n" +
                "   <td>Pierre</td>\n" +
                "   <td>GEA</td>\n" +
                "   <td>       <a href=\"detail?id=2\">Details</a>       <a href=\"delete?id=2\">Delete</a>   </td>\n" +
                "</tr><tr>\n" +
                "   <td>3</td>\n" +
                "   <td><a href=\"detail?id=3\">Patrick</a></td>\n" +
                "   <td>Pascal</td>\n" +
                "   <td>INFO</td>\n" +
                "   <td>       <a href=\"detail?id=3\">Details</a>       <a href=\"delete?id=3\">Delete</a>   </td>\n" +
                "</tr><tr>\n" +
                "   <td>4</td>\n" +
                "   <td><a href=\"detail?id=4\">Jeanne</a></td>\n" +
                "   <td>Doe</td>\n" +
                "   <td>TC1</td>\n" +
                "   <td>       <a href=\"detail?id=4\">Details</a>       <a href=\"delete?id=4\">Delete</a>   </td>\n" +
                "</tr><tr>\n" +
                "   <td>5</td>\n" +
                "   <td><a href=\"detail?id=5\">John</a></td>\n" +
                "   <td>Doe</td>\n" +
                "   <td>CASIR</td>\n" +
                "   <td>       <a href=\"detail?id=5\">Details</a>       <a href=\"delete?id=5\">Delete</a>   </td>\n" +
                "</tr></table></body></html>";

        xmlToHTMLParser.parse("list.xml");

        assertEquals(expected.trim(), xmlToHTMLParser.getHtml().trim());
    }

}