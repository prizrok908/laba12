package com.example.domain;

import java.sql.SQLOutput;

public class Employee {
    public int empId;
    public String name;
    public String ssn;
    public double salary;

    public Employee(String name, String ssn, int empId, double salary) {
        this.name = name;
        this.ssn = ssn;
        this.empId = empId;
        this.salary = salary;
    }

  //  public Employee() {}

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void display(){
        System.out.println(empId);
        System.out.println(ssn);
        System.out.println(name);
        System.out.println(salary);

    }
}