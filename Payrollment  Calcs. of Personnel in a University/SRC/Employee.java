import java.text.DecimalFormat;

public class Employee extends Personnel {
    protected double severancePay;
    private double overWorkSalary;

    // constructor
    public Employee(String name, String surname, String registerNumber, String position, int yearOfStart) {
        super(name, surname, registerNumber, position, yearOfStart);
        calculateSeverancePay();
    }

    @Override
    public String calculateSalary(int[] hoursWorked) {
        int maxWeeklyOverWorkHoursWorker = 10;
        int maxWeeklyOverWorkHoursChief = 8;
        double hourlyRateForWorker = 11;
        double hourlyRateForChief = 15;
        double totalSalary = 0;

        for (int j = 0; j < 4; j++) {
            int overWorkHours = hoursWorked[j] - 40;
            if (overWorkHours > 0) {
                if ("WORKER".equals(position)) {
                    overWorkSalary = overWorkHours * hourlyRateForWorker;

                    double overWorkedHours = Math.min(overWorkHours, maxWeeklyOverWorkHoursWorker);
                    double overWorkSalary = overWorkedHours * hourlyRateForWorker;
                    totalSalary += overWorkSalary;
                }else if ("CHIEF".equals(position)) {
                    overWorkSalary = overWorkHours * hourlyRateForChief;

                    double overWorkedHours = Math.min(overWorkHours, maxWeeklyOverWorkHoursChief);
                    double overWorkSalary = overWorkedHours * hourlyRateForChief;
                    totalSalary += overWorkSalary;
            }
        }
        }
        if ("WORKER".equals(position)) {
            totalSalary += calculateDailySalary();
        } else if ("CHIEF".equals(position)) {
            totalSalary += calculateDailySalary();
        }
        totalSalary += severancePay;


        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String formattedTotalSalary = decimalFormat.format(totalSalary);
        formattedTotalSalary = formattedTotalSalary.replace(',', '.');
        return formattedTotalSalary;
    }


    protected void calculateSeverancePay() {
        int pointsPerYear = 20;
        double multiplier = 0.8;
        int currentYear = java.time.Year.now().getValue();
        this.severancePay = (currentYear - this.yearOfStart) * pointsPerYear * multiplier;
    }

    // helper method to calculate daily salary for Full-time Employees
    private double calculateDailySalary() {
        double dailySalary;
        if ("WORKER".equals(position)) {
            dailySalary = 20 * 105;
        } else if ("CHIEF".equals(position)) {
            dailySalary = 20 * 125;
        } else {
            dailySalary = 0;
        }
        return dailySalary;
    }
}