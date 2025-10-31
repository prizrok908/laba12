package com.example.domain;

public class Manager extends Employee {
    private String department;

    public Manager() {}

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}