package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities_enums.WorkerLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainUI {

    private final Scanner sc = new Scanner(System.in);
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MainUI(){

    }

    public void mainMenu(){
        System.out.print("Enter department's name: ");
        String departmentName = sc.nextLine();

        System.out.println("Enter worker data: ");
        System.out.print("Name: ");
        String workerName = sc.nextLine();
        System.out.print("Level: ");
        String workerLevel = sc.nextLine().toUpperCase();
        System.out.print("Base salary: ");
        double baseSalary = Double.parseDouble(sc.nextLine());

        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));

        System.out.print("\nHow many contracts to his worker? ");
        int n = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < n; i++){
            System.out.println("\nEnter contract #" + (i+1) +" data:");
            System.out.print("Date (DD/MM/YYYY): ");
            String date = sc.next();
            LocalDate contractDate = LocalDate.parse(date, dtf);
            System.out.print("Value per hour: ");
            double valueHour = sc.nextDouble();
            System.out.print("Duration(hours): ");
            int hours = sc.nextInt();

            HourContract contract = new HourContract(contractDate, valueHour, hours);
            worker.addContract(contract);
        }

        System.out.print("\nEnter month and year to calculate income(MM/YYYY): ");
        String monthAndYear = sc.next();

        int month = Integer.parseInt(monthAndYear.substring(0,2));
        int year = Integer.parseInt(monthAndYear.substring(3));

        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartment().getDepartmentName());
        System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

        sc.close();
    }
}
