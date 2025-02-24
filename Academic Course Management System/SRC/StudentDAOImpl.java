import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private List<Student> students = new ArrayList<>();

    @Override
    public Student getByID(int studentID) {
        for (Student student : students){
            if (student.getStudentId() == studentID){
                return student;
            }
        }
        return null;
    }

    @Override
    public void deleteByID(int studentID) {
    }

    @Override
    public void add(Student student) {
        students.add(student);
    }

    @Override
    public ArrayList<Student> getAll() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {

                return student1.getStudentName().compareTo(student2.getStudentName());
            }
        });

        System.out.print("Student List:");

        for (Student student : students) {
            System.out.println(student.toString());
}
        return new ArrayList<>(students);
}
}