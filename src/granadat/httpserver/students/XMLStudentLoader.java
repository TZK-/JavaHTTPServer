package granadat.httpserver.students;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XMLStudentLoader implements StudentsLoader {

    @Override
    public Map<Integer, Student> load(String name) {

        Map<Integer, Student> students = new HashMap<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document doc = documentBuilder.parse(new File(name));
            NodeList nodeList = doc.getElementsByTagName("student");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {

                Node node = nodeList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element student = (Element) node;
                    int id = Integer.parseInt(student.getElementsByTagName("id").item(0).getTextContent());
                    String lastName = student.getElementsByTagName("lastname").item(0).getTextContent();
                    String firstName = student.getElementsByTagName("firstname").item(0).getTextContent();
                    String group = student.getElementsByTagName("group").item(0).getTextContent();

                    students.put(id, new Student(id, lastName, firstName, group));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}

