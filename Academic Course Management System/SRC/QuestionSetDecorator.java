public class QuestionSetDecorator extends Assessment {


    public QuestionSetDecorator(Assessment assessment) {
        super(String.valueOf(assessment),7);
    }

    @Override
    public double calculateAssessmentFee() {

        return 7;
    }

    @Override
    public void printTasks() {
    }
}
