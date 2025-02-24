import java.text.DecimalFormat;

public class Security extends Personnel {
    private double severancePay;
    private double transMoney = 5;
    private double foodMoney = 10;
    private int maxDailyWorkHours = 9;
    private int minDailyWorkHours = 5;
    private int weeklyWorkingDays = 6;
    private double hourlyRate = 10;

    // constructor
    public Security(String name, String surname, String registerNumber, String position, int yearOfStart) {
        super(name, surname, registerNumber, position, yearOfStart);
        calculateSeverancePay();
    }



    @Override
    public String calculateSalary(int[] hoursWorked) {
        double totalSalary = 0;
        for (int i = 0; i < 4; i++) {
            double actualHoursWorked = Math.min(hoursWorked[i], maxDailyWorkHours * weeklyWorkingDays);
            if (actualHoursWorked > minDailyWorkHours * weeklyWorkingDays) {
                totalSalary += actualHoursWorked  * hourlyRate;
            }




        }
        totalSalary += 4 * (transMoney * weeklyWorkingDays + foodMoney * weeklyWorkingDays);
        totalSalary += severancePay;

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String formattedTotalSalary = decimalFormat.format(totalSalary);
        formattedTotalSalary = formattedTotalSalary.replace(',', '.');
        return formattedTotalSalary;
    }


    private void calculateSeverancePay() {
        int pointsPerYear = 20;
        double multiplier = 0.8;
        int currentYear = java.time.Year.now().getValue();

        severancePay = (currentYear - yearOfStart) * pointsPerYear * multiplier;
    }
}

