public class AnalysisDecorator extends Assessment {

    public AnalysisDecorator(Assessment assessment) {
        super(String.valueOf(assessment),0);
    }

    @Override
    public double calculateAssessmentFee() {
        return 0;
    }

    @Override
    public void printTasks() {
    }
}