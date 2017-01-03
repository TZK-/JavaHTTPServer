package granadat.httpserver.students;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLStudentExporter implements Exportable {

    private final StudentsManager studentsManager;

    public XMLStudentExporter(StudentsManager studentsManager) {
        this.studentsManager = studentsManager;
    }

    @Override
    public void export(String path) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;

        docBuilder = docFactory.newDocumentBuilder();

        assert docBuilder != null;

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("students");
        doc.appendChild(rootElement);

        for (Student student : studentsManager.getStudents().values()) {
            Element studentNode = doc.createElement("student");
            rootElement.appendChild(studentNode);

            Element idNode = doc.createElement("id");
            idNode.appendChild(doc.createTextNode(Integer.toString(student.getId())));
            studentNode.appendChild(idNode);

            Element lastNameNode = doc.createElement("lastname");
            lastNameNode.appendChild(doc.createTextNode(student.getLastName()));
            studentNode.appendChild(lastNameNode);

            Element firstNameNode = doc.createElement("firstname");
            firstNameNode.appendChild(doc.createTextNode(student.getFirstName()));
            studentNode.appendChild(firstNameNode);

            Element groupNope = doc.createElement("group");
            groupNope.appendChild(doc.createTextNode(student.getGroup()));
            studentNode.appendChild(groupNope);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;

        transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("students.xml"));

        transformer.transform(source, result);

    }
}
