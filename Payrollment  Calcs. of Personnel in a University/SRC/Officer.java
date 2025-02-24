import java.text.DecimalFormat;

public class Officer extends Personnel {
    private double baseSalary = 2600;
    private double ssBenefits = baseSalary * 0.65;
    private double severancePay;
    private double overWorkSalary;



    public Officer(String name, String surname, String registerNumber, String position, int yearOfStart) {
        super(name, surname, registerNumber, position, yearOfStart);
        calculateSeverancePay();
    }



    public String calculateSalary(int[] hoursWorked) {
        int maxWeeklyOverWorkHours = 10;
        double hourlyRate = 20;
        int i = -40;
        double totalSalary = baseSalary + ssBenefits;
        for (int j = 0; j < 4; j++) {
            int overWorkHours = hoursWorked[j] - 40;
            if (overWorkHours > 0) { // Only calculate overtime if there are overtime hours


                double overWorkedHours = Math.min(overWorkHours, maxWeeklyOverWorkHours);
                double overWorkSalary = overWorkedHours * hourlyRate;
                totalSalary += overWorkSalary;
            }
        }
        totalSalary += severancePay;

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String formattedTotalSalary = decimalFormat.format(totalSalary);
        formattedTotalSalary = formattedTotalSalary.replace(',', '.');
        return formattedTotalSalary;
    }

    // helper method to calculate severance pay
    private void calculateSeverancePay() {
        int pointsPerYear = 20;
        double multiplier = 0.8;
        int currentYear = java.time.Year.now().getValue();


        severancePay = (currentYear - yearOfStart) * pointsPerYear * multiplier;
    }
}
