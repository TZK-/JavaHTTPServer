package granadat.httpserver.students;

import java.util.Map;

public interface StudentsLoader {

    Map<Integer, Student> load(String name);

}
