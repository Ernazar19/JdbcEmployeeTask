package peaksoft;
import peaksoft.model.Employee;
import peaksoft.model.Job;
import peaksoft.service.EmployeeService;
import peaksoft.service.JobService;
import peaksoft.service.serviceImpl.EmployeeServiceImpl;
import peaksoft.service.serviceImpl.JobServiceImpl;
import java.util.Scanner;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        JobService jobService = new JobServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        Scanner scanner = new Scanner(System.in);
        Scanner scannerForNumber = new Scanner(System.in);
        while (true) {
            switch (scanner.next()) {
                case "1", "jobTable" -> jobService.createJobTable();
                case "2", "addJob" -> {
                    System.out.println("Enter position: ");
                    String position = scanner.nextLine();
                    System.out.println("Enter profession: ");
                    String profession = scanner.nextLine();
                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.println("Enter experience: ");
                    int experience = scannerForNumber.nextInt();
                    jobService.addJob(new Job(position, profession, description, experience));
                }
                case "3", "getJobById" -> {
                    System.out.print("Enter job_ID: ");
                    Long jobId = scannerForNumber.nextLong();
                    System.out.println(jobService.getJobById(jobId));
                }

                case "4", "sortByExperience" -> {
                    String sort = scanner.nextLine();
                    if (sort.equalsIgnoreCase("asc")) {
                        System.out.println(jobService.sortByExperience(sort));
                    } else if (sort.equalsIgnoreCase("desc")) {
                        System.out.println(jobService.sortByExperience(sort));
                    } else {
                        System.err.println("error");
                    }

                }
                case "5", "getJobEmployeeId" -> {
                    System.out.print("Enter employee_id: ");
                    Long employeeId = scannerForNumber.nextLong();
                    System.out.println(jobService.getJobByEmployeeId(employeeId));
                }

                case "6", "deleteDescriptionColumn" -> jobService.deleteDescriptionColumn();
                case "7", "createTable Employee" -> employeeService.createEmployee();
                case "8", "addEmployee" -> {
                    System.out.print("Enter first_Name: ");
                    String firstName = scannerForNumber.nextLine();
                    System.out.print("Enter last_name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scannerForNumber.nextInt();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter job_Id");
                    Long jobId = scannerForNumber.nextLong();
                    employeeService.addEmployee(new Employee(firstName, lastName, age, email, jobId));
                }
                case "9", "dropTable" -> employeeService.dropTable();
                case "10", "cleanTable" -> employeeService.cleanTable();
                case "11", "updateEmployeeById" -> {
                    System.out.print("Enter employee_id: ");
                    Long employeeId = scannerForNumber.nextLong();
                    System.out.print("Enter first_Name: ");
                    String firstName = scannerForNumber.nextLine();
                    System.out.print("Enter last_name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scannerForNumber.nextInt();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter job_Id");
                    Long jobId = scannerForNumber.nextLong();
                    employeeService.updateEmployee(employeeId, new Employee(firstName, lastName, age, email, jobId));
                }
                case "12", "findById" -> {
                    System.out.print("Enter employee_id: ");
                    String employeeEmail = scanner.nextLine();
                    System.out.println(employeeService.findByEmail(employeeEmail));
                }
                case "13", "getById" -> {
                    System.out.print("Enter employee_id: ");
                    Long employeeId = scannerForNumber.nextLong();
                    System.out.println(employeeService.getEmployeeById(employeeId));
                }
                case "14", "getEmployeeByPosition" -> {
                    System.out.println("Enter position: ");
                    String position = scanner.nextLine();
                    employeeService.getEmployeeByPosition(position);
                }


            }
        }
    }
}
