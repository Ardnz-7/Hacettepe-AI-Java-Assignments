import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private List<Enrollment> enrollments;
    public Student(int studentId, String studentName, String studentSurname, String phoneNumber, String address) {
        super(studentId, studentName, studentSurname, phoneNumber, address);
        this.enrollments = new ArrayList<>();
    }
    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
    }

    public List<Enrollment> listEnrollments() {
        return enrollments;
    }
}
