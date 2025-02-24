import java.util.ArrayList;

public interface DAO<T> {
    T getByID(int ID);

    T deleteByID(int ID);

    void add(T object);

    ArrayList<T> getAll();
}