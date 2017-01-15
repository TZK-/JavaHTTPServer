package granadat.httpserver.students;

import java.util.Map;

public class StudentsManager {

    private Map<Integer, Student> students;

    private StudentsLoader studentsLoader;

    public StudentsManager(StudentsLoader studentsLoader, String name) {
        this.studentsLoader = studentsLoader;
        this.students = this.studentsLoader.load(name);
    }

    public Student getStudentById(int id) {
        return this.students.get(id);
    }

    public void addStudent(Student student) {
        this.students.put(student.getId(), student);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Student student : this.students.values()) {
            stringBuilder.append(student);
            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }

    public Map<Integer, Student> getStudents() {
        return this.students;
    }

    public void save(Exportable exporter) {
        try {
            exporter.export("students.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
