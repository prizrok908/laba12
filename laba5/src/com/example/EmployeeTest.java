package com.example;

import com.example.domain.Developer;
import com.example.domain.Intern;
import com.example.domain.Manager;
import com.example.domain.Employee;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setEmpId(101);
        emp.setName("Jane Smith");
        emp.setSalary(120_345.27);
        emp.setSsn("012-34-5678");

        System.out.println("Employee ID: " + emp.getEmpId());
        System.out.println("Employee Name: " + emp.getName());
        System.out.println("Employee Soc Sec # " + emp.getSsn());
        System.out.println("Employee salary: " + emp.getSalary());

        Developer dev = new Developer();
        dev.setEmpId(201);
        dev.setName("Алексей");
        dev.setSalary(150_000);
        dev.setProgrammingLanguage("Java");

        System.out.println("\n=== Разработчик ===");
        System.out.println("Имя: " + dev.getName());
        System.out.println("Язык: " + dev.getProgrammingLanguage());
        System.out.println("ЗП: " + dev.getSalary());

        Manager mgr = new Manager();
        mgr.setEmpId(301);
        mgr.setName("Ольга");
        mgr.setSalary(180_000);
        mgr.setDepartment("IT");

        System.out.println("\n=== Менеджер ===");
        System.out.println("Имя: " + mgr.getName());
        System.out.println("Департамент: " + mgr.getDepartment());

        Intern intern = new Intern();
        intern.setEmpId(401);
        intern.setName("Студент Иван");
        intern.setUniversity("МГУ");
        intern.setSalary(30_000);

        System.out.println("\n=== Стажёр ===");
        System.out.println("Имя: " + intern.getName());
        System.out.println("Университет: " + intern.getUniversity());
        System.out.println("ЗП: " + intern.getSalary());
    }
}