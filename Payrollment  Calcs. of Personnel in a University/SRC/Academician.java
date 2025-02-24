import java.text.DecimalFormat;

public class Academician extends  Personnel {

    private double baseSalary = 2600;
    private double ssBenefits = baseSalary * 1.35 ;
    protected double severancePay;




    public Academician(String name, String surname, String registerNumber, String position, int yearOfStart) {
        super(name, surname, registerNumber, position, yearOfStart);
        calculateSeverancePay();
    }

    public String calculateSalary(int[] hoursWorked){
        int maxAdditionalCourseHours = 8;
        double hourlyRate = 20;

        double totalSalary = baseSalary + ssBenefits;

        for (int j = 0; j < 4; j++) {
            int overWorkHours = hoursWorked[j] - 40;
            if (overWorkHours > 0) { // Only calculate overtime if there are overtime hours


                double overWorkedHours = Math.min(overWorkHours, maxAdditionalCourseHours);
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
     void calculateSeverancePay() {

        int pointsPerYear = 20;
        double multiplier = 0.8;
         int currentYear = java.time.Year.now().getValue();

        this.severancePay = (currentYear - this.yearOfStart) * pointsPerYear * multiplier;
    }
}
