package granadat.httpserver.students;

public class Student {

    private int id;
    private String lastName;
    private String firstName;
    private String group;

    public Student(int id, String lastName, String firstName, String group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.group = group;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s : %s", this.id, this.lastName, this.firstName, this.group);
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
