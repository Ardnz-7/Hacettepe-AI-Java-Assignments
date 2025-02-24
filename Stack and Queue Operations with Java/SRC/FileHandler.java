import java.io.*;
import java.util.*;
public class FileHandler {
    public static List<Integer> readFromFile(String filename) {
        List<Integer> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                data.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeToFile(String filename, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}