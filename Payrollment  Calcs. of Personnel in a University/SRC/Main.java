import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String personnel = args[0];
        String monitoring = args[1];


        List<Personnel> personnelList = readPersonnelInfo(personnel);


        readWorkingHours(monitoring, personnelList);

        writeTotalSalaries(personnelList);
    }

    private static List<Personnel> readPersonnelInfo(String filename) {
        List<Personnel> personnelList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String[] nameAndSurnameParts = parts[0].split(" ");
                String name = nameAndSurnameParts[0];
                String surname = nameAndSurnameParts[1];
                String registrationNumber = parts[1];
                String position = parts[2];
                int yearOfStart = Integer.parseInt(parts[3]);

                switch (position) {
                    case "FACULTY_MEMBER":
                        personnelList.add(new Academician(name, surname, registrationNumber, position, yearOfStart));
                        break;
                    case "WORKER":
                        personnelList.add(new Employee(name, surname, registrationNumber, position, yearOfStart));
                        break;
                    case "SECURITY":
                        personnelList.add(new Security(name, surname, registrationNumber, position, yearOfStart ));
                        break;
                    case "OFFICER":
                        personnelList.add(new Officer(name, surname, registrationNumber, position, yearOfStart));
                        break;
                    case "CHIEF":
                        personnelList.add(new Employee(name, surname, registrationNumber, position, yearOfStart));
                        break;
                    case "PARTTIME_EMPLOYEE":
                        personnelList.add(new PartTimeEmployee(name, surname, registrationNumber, position, yearOfStart));
                        break;
                    case "RESEARCH_ASSISTANT":
                        personnelList.add(new ResearchAssistant(name, surname, registrationNumber, position, yearOfStart));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return personnelList;
    }

    private static void readWorkingHours(String filename, List<Personnel> personnelList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String registrationNumber = parts[0];

                for (Personnel personnel : personnelList) {
                    if (personnel.getRegistrationNumber().equals(registrationNumber)) {
                        int[] hoursWorked = new int[4];
                        for (int i = 0; i < 4; i++) {
                            hoursWorked[i] = Integer.parseInt(parts[i + 1]);
                        }
                        personnel.setHoursWorked(hoursWorked);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeTotalSalaries(List<Personnel> personnelList) {
        try {
            for (Personnel personnel : personnelList) {
                String outputFileName = personnel.getRegistrationNumber() + ".txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    writer.write("Name : " + personnel.getName());
                    writer.newLine();
                    writer.newLine();
                    writer.write("Surname : " + personnel.getSurname());
                    writer.newLine();
                    writer.newLine();
                    writer.write("Registration Number : " + personnel.getRegistrationNumber());
                    writer.newLine();
                    writer.newLine();
                    writer.write("Position : " + personnel.getPosition());
                    writer.newLine();
                    writer.newLine();
                    writer.write("Year of Start : " + personnel.getYearOfStart());
                    writer.newLine();
                    writer.newLine();
                    writer.write("Total Salary : " + personnel.calculateSalary(personnel.hoursWorked) + " TL");
                    writer.newLine();
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
