package granadat.httpserver.html;

import granadat.httpserver.students.Student;
import granadat.httpserver.students.StudentsManager;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLToHTMLHandler extends DefaultHandler {

    public static String html;

    private StudentsManager manager;

    public XMLToHTMLHandler(StudentsManager manager) {
        this.manager = manager;
    }

    @Override
    public void startDocument() throws SAXException {
        XMLToHTMLHandler.html = "";
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch(qName) {
            case "mapping":
                html += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\" lang=\"fr\">\n" +
                        "    <head>\n" +
                        "        <title>"+ attributes.getValue("title") +"</title>\n" +
                        "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"/>\n" +
                        "    </head>\n" +
                        "    <body>";
                break;
            case "list":
                html += "<table class=\"table table-striped\">\n" +
                        "   <tr>";
                switch(attributes.getValue("source")) {
                    case "students":
                        html += "<th>Id</th>\n" +
                                "<th>LastName</th>\n" +
                                "<th>FirstName</th>\n" +
                                "<th>Group</th>\n" +
                                "<th>Action</th>\n" +
                                "</tr>";
                        for (Student student : this.manager.getStudents().values()) {
                            html += "<tr>\n" +
                                    "   <td>"+ student.getId() +"</td>\n" +
                                    "   <td><a href=\"detail?id=" + student.getId() + "\">" + student.getLastName() + "</a></td>\n" +
                                    "   <td>"+ student.getFirstName() +"</td>\n" +
                                    "   <td>"+ student.getGroup() +"</td>\n" +
                                    "   <td>" +
                                    "       <a href=\"detail?id=" + student.getId() + "\">Details</a>" +
                                    "       <a href=\"delete?id=" + student.getId() + "\">Delete</a>" +
                                    "   </td>\n" +
                                    "</tr>";
                        }
                }
                break;
            case "column":
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "mapping":
                html += "</body></html>";
                break;

            case "list":
                html += "</table>";
                break;
        }

    }
}
