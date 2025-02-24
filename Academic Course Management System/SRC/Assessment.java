import java.util.List;

public class Assessment {
    private String assessmentType;
    private double baseFee;
    private List<String> tasks;

    public Assessment(String assessmentType, double baseFee) {
        this.assessmentType = assessmentType;
        this.baseFee = baseFee;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public double calculateAssessmentFee() {
        return baseFee;
    }

    public void addTask(String task) {
        tasks.add(task);
    }
    public void printTasks() {
        System.out.println("Tasks: " + String.join(", ", tasks));
    }

    public void setTasks(List<String> task) {
        tasks = task;
    }
}
