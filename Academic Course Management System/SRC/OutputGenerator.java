import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputGenerator {

    private static final String OUTPUT_FILE_PATH = "output.txt";

    public static void generateOutput(String output) {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE_PATH, true)) {
            writer.write(output + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateStudentListOutput(List<Student> students) {
        generateOutput("Student List:");
        for (Student student : students) {
            String output = student.getStudentId() + " " +
                    student.getStudentName() + " " +
                    student.getStudentSurname() + " " +
                    student.getPhoneNumber() + " " +
                    "Address: " + student.getAddress();
            generateOutput(output);
        }
        generateOutput("");
    }

    public static void generateTotalFeeOutput(int enrollmentID, String assessmentType, List<String> tasks, double totalFee) {
        generateOutput("TotalFee for enrollment " + enrollmentID);
        generateOutput("\t" + assessmentType + " " + String.join(" ", tasks) + " " + totalFee);
        generateOutput("\tTotal: " + totalFee);
    }

}
