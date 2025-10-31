package com.example.domain;

public class Intern extends Employee {
    private String university;

   // public Intern() {}

    public Intern(String name, String ssn, int empId, double salary, String university) {
        super(name, ssn, empId, salary);
        this.university = university;
    }



    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }
}