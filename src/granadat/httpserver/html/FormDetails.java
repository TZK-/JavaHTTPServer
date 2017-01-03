package granadat.httpserver.html;

import granadat.httpserver.students.Student;

public class FormDetails implements Htmlable {

    private final Student student;

    public FormDetails(Student student) {
        this.student = student;
    }

    @Override
    public String getHtml() {
        return String.format("<form action=\"updateStudent\" method=\"get\">\n" +
                        "    <input type=\"hidden\" value=\"%s\" name=\"id\"/>\n" +
                        "    <input type=\"text\" value=\"%s\" name=\"first_name\" placeholder=\"First name\"/>\n" +
                        "    <input type=\"text\" value=\"%s\" name=\"last_name\" placeholder=\"Last name\"/>\n" +
                        "    <input type=\"text\" value=\"%s\" name=\"group\" placeholder=\"Group\"/>\n" +
                        "    <button type=\"submit\" class=\"\">Update</button>\n" +
                        "</form>",
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGroup()
        );
    }
}
