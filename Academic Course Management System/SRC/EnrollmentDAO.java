import java.util.List;

public interface EnrollmentDAO {
    Enrollment getID(int ID);

    void deleteByID(int ID);

    void add(Enrollment enrollment);

    List<Enrollment> getAll();
}