import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    private static StudentDAO studentDAO = new StudentDAOImpl();
    public static void main(String[] args) {

        String inputFilePath = args[0];
        String inputFilePath1 = "student.txt";
        readStudentData(inputFilePath1);
        String filePath = "courseEnrollment.txt";
        List<Enrollment> enrollments = EnrollmentFileReader.readEnrollmentFile(filePath);


        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process each command from the input file
                processCommand(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processCommand(String command) {
        String[] tokens = command.split("\\s+");
        String action = tokens[0];

        switch (action) {
            case "AddStudent":
                handleAddStudentCommand(tokens);
                break;
            case "RemoveStudent":
                handleRemoveStudentCommand(tokens);
                break;
            case "CreateEnrollment":
                handleCreateEnrollmentCommand(tokens);
                break;
            case "ListStudents":
                OutputGenerator.generateStudentListOutput(studentDAO.getAll());
                break;
            case "AddAssessment":
                handleAddAssessmentCommand(tokens);
                break;

        }
    }
    public static void readStudentData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                int studentID = Integer.parseInt(parts[0]);
                String[] nameParts = parts[1].split(" ");
                String name = nameParts[0];
                String surname = nameParts[1];
                String phoneNumber = parts[2];
                String address = parts[3].substring("Address: ".length());

                Student student = new Student(studentID, name, surname, phoneNumber, address);
                studentDAO.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void handleAddStudentCommand(String[] tokens) {
        // Parse tokens and create a new student

        int studentID = Integer.parseInt(tokens[1]);
        String name = tokens[2];
        String surname = tokens[3];
        String phoneNumber = tokens[4];
        StringBuilder addressBuilder = new StringBuilder();
        for (int i = 4; i < tokens.length; i++) {
            addressBuilder.append(tokens[i]);
            if (i < tokens.length - 1) {
                addressBuilder.append(" ");
            }
        }
        String address = addressBuilder.toString();

        Student newStudent = new Student(studentID, name, surname, phoneNumber, address);
        studentDAO.add(newStudent);

        String output = "Student " + newStudent.getStudentId() + " " + newStudent.getStudentName() + " added";
        OutputGenerator.generateOutput(output);

    }

    private static void handleRemoveStudentCommand(String[] tokens) {
        int studentID = Integer.parseInt(tokens[1]);

        studentDAO.deleteByID(studentID);

        String output = "Student " + studentID + " removed";
        OutputGenerator.generateOutput(output);
    }

    private static void handleCreateEnrollmentCommand(String[] tokens) {

        int enrollmentID = Integer.parseInt(tokens[1]);
        int studentID = Integer.parseInt(tokens[2]);

        Student student = studentDAO.getByID(studentID);

        if (student != null) {

            EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();
            Enrollment newEnrollment = new Enrollment(enrollmentID, student.getStudentId());
            enrollmentDAO.add(newEnrollment);

            String output = "CourseEnrollment " + enrollmentID + " created";
            OutputGenerator.generateOutput(output);
        } else {
            System.out.println("Error: Student with ID " + studentID + " not found.");
        }
    }
    private static void handleAddAssessmentCommand(String[] tokens) {
        int enrollmentID = Integer.parseInt(tokens[1]);
        String assessmentType = tokens[2];
        String[] tasks = Arrays.copyOfRange(tokens, 3, tokens.length);

        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl(); // Adjust based on your actual implementation


            Assessment assessment = createAssessment(assessmentType, tasks);

            String output = assessmentType + " assessment added to enrollment " + enrollmentID;
            OutputGenerator.generateOutput(output);

    }
    private static Assessment createAssessment(String type, String[] tasks) {

        Assessment assessment;

        switch (type) {
            case "Essaybased":
                assessment = new EssayBasedAssessment("Essaybased Assessment", 10);
                break;
            case "MultipleChoice":
                assessment = new MultipleChoiceAssessment("MultipleChoice Assessment", 15);
                break;
            default:
                throw new IllegalArgumentException("Invalid assessment type: " + type);
        }
        for (String task : tasks) {
            switch (task) {
                case "LiteratureReview":
                    assessment = new LiteratureReviewDecorator(assessment);
                    break;
                case "Analysis":
                    assessment = new AnalysisDecorator(assessment);
                    break;
                case "QuestionSet":
                    assessment = new QuestionSetDecorator(assessment);
                    break;
                case "AdditionalTasks":
                    assessment = new AdditionalTasksDecorator(assessment);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task type: " + task);
            }
        }

        return assessment;
    }

    }