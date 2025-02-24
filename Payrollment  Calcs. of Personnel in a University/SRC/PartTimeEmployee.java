import java.text.DecimalFormat;

public class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, String surname, String registerNumber, String position, int yearOfStart) {
        super(name, surname, registerNumber, position, yearOfStart);
        calculateSeverancePay();
    }
    @Override
    public String calculateSalary(int[] hoursWorked) {
        int minWeeklyHours = 10;
        int maxWeeklyHours = 20;
        double hourlyRate = 18;
        double totalSalary = 0;
        for (int i = 0; i < 4; i++) {
            double actualHoursWorked = Math.min(hoursWorked[i], maxWeeklyHours);
            if (actualHoursWorked >= minWeeklyHours) {
                totalSalary += actualHoursWorked * hourlyRate;
            }
        }
        totalSalary += severancePay;

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String formattedTotalSalary = decimalFormat.format(totalSalary);
        formattedTotalSalary = formattedTotalSalary.replace(',', '.');
        return formattedTotalSalary;
    }
}