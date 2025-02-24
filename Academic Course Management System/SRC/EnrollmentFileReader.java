import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class EnrollmentFileReader {

    public static List<Enrollment> readEnrollmentFile(String filePath) {
        List<Enrollment> enrollments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Enrollment currentEnrollment = null;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\t");
                if (tokens.length == 2 && tokens[0].matches("\\d+") && tokens[1].matches("\\d+")) {
                    int enrollmentID = Integer.parseInt(tokens[0]);
                    int studentID = Integer.parseInt(tokens[1]);

                    if (currentEnrollment == null || currentEnrollment.getEnrollmentID() != enrollmentID) {
                        currentEnrollment = new Enrollment(enrollmentID, studentID);
                        enrollments.add(currentEnrollment);
                    }

                    readAssessments(br, currentEnrollment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return enrollments;
    }

    private static void readAssessments(BufferedReader br, Enrollment enrollment) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] tokens = line.split("\\t");
            String assessmentType = tokens[0];
            List<String> tasks = Arrays.asList(tokens[1].split(" "));

            if (tokens[0].equals("MultipleChoice") || tokens[0].equals("Essaybased")){
                Assessment assessment;
                if (assessmentType.equals("MultipleChoice")) {
                    assessment = new Assessment("MultipleChoice", 15);
                } else if (assessmentType.equals("Essaybased")) {
                    assessment = new EssayBasedAssessment("Essaybased", 10);
                } else {
                    throw new IllegalArgumentException("Invalid assessment type: " + assessmentType);
                }

                assessment.setTasks(tasks);
                enrollment.addAssessment(assessment);
            } else break;


        }
    }
}