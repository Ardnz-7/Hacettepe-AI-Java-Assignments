import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {
    private List<Enrollment> enrollments;

    public EnrollmentDAOImpl() {
        this.enrollments = new ArrayList<>();
    }

    @Override
    public Enrollment getID(int ID) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getID() == ID) {
                return enrollment;
            }
        }
        return null;
    }

    @Override
    public void deleteByID(int ID) {
        Enrollment enrollmentToRemove = getID(ID);
        if (enrollmentToRemove != null) {
            enrollments.remove(enrollmentToRemove);
        }
    }

    @Override
    public void add(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    @Override
    public List<Enrollment> getAll() {
        return new ArrayList<>(enrollments);
    }
}
