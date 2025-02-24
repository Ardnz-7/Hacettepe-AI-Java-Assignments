public class LiteratureReviewDecorator extends Assessment {
    public LiteratureReviewDecorator(Assessment assessment) {
        super(String.valueOf(assessment),15);
    }

    @Override
    public double calculateAssessmentFee() {
        return super.calculateAssessmentFee() + 15;
    }

    @Override
    public void printTasks() {
        super.printTasks();
        System.out.println("LiteratureReview");
    }
}
