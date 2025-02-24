import java.util.List;
public interface StudentDAO {
        Student getByID(int ID);

        void deleteByID(int ID);

        void add(Student student);

        List<Student> getAll();
}