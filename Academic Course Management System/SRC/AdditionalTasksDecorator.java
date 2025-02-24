public class AdditionalTasksDecorator extends Assessment {
    public AdditionalTasksDecorator(Assessment assessment) {
        super(String.valueOf(assessment), 5);
    }

    @Override
    public double calculateAssessmentFee() {
        return super.calculateAssessmentFee() + 5;
    }

    @Override
    public void printTasks() {
        super.printTasks();
        System.out.println("AdditionalTasks");
    }
}
