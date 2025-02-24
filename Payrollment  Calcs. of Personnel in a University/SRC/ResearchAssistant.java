import java.text.DecimalFormat;

public class ResearchAssistant extends Academician {
    private double baseSalary = 2600;
    private double ssBenefits = baseSalary * 1.05;
    public ResearchAssistant(String name, String surname, String registerNumber, String position, int yearOfStart) {
        super(name, surname, registerNumber, position, yearOfStart);
        calculateSeverancePay();
    }
    public String calculateSalary(int[] hoursWorked) {
        calculateSeverancePay();

        double totalSalary = baseSalary + ssBenefits+ severancePay;

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String formattedTotalSalary = decimalFormat.format(totalSalary);
        formattedTotalSalary = formattedTotalSalary.replace(',', '.');
        return formattedTotalSalary;
    }
}
