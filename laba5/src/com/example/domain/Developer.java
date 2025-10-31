package com.example.domain;

public class Developer extends Employee {
    private String programmingLanguage;

    public Developer() {}

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}