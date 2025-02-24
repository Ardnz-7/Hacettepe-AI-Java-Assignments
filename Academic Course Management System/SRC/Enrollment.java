import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private List<Assessment> assessments;

    public Enrollment(int enrollmentId, int studentId) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.assessments = new ArrayList<>();
    }

    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

    public double calculateTotalFees() {
        double totalFees = 0.0;
        for (Assessment assessment : assessments) {
            totalFees += assessment.calculateAssessmentFee();
        }
        return totalFees;
    }

    public int getID() {
        return enrollmentId;
    }

    public void setID(int id) {
        this.enrollmentId = id;
    }

    public int getEnrollmentID() {
        return enrollmentId;
    }

}
