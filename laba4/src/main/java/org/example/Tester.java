package org.example;

public class Tester {
    private String name;
    private String surname;
    private int experienceInYears;
    private String englishLevel;
    private double salary;
    static public String p;

    public Tester(String name, String surname, int experienceInYears, String englishLevel, double salary) {
        this.name = name;
        this.surname = surname;
        this.experienceInYears = experienceInYears;
        this.englishLevel = englishLevel;
        this.salary = salary;
    }

    public Tester(String name, String surname, int experienceInYears, String englishLevel) {
        this(name, surname, experienceInYears, englishLevel, 0.0);
    }

    public Tester(String name, String surname) {
        this(name, surname, 0, "A1");
    }

    public void increaseSalary(double amount) {
        this.salary += amount;
        System.out.println("Зарплата увеличена на " + amount + ". Новая зарплата: " + this.salary);
    }

    public void increaseSalary(double percent, boolean isPercent) {
        if (isPercent) {
            this.salary += this.salary * (percent / 100);
            System.out.println("Зарплата увеличена на " + percent + "%. Новая зарплата: " + this.salary);
        }
    }

    public void increaseSalary(double amount, String reason) {
        this.salary += amount;
        System.out.println(p);
        System.out.println("Зарплата увеличена на " + amount + " по причине: " + reason + ". Новая зарплата: " + this.salary);
    }

    public static String getJobTitle() {
        return "Software Tester";
    }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public int getExperienceInYears() { return experienceInYears; }
    public String getEnglishLevel() { return englishLevel; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Tester{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", experienceInYears=" + experienceInYears +
                ", englishLevel='" + englishLevel + '\'' +
                ", salary=" + salary +
                '}';
    }
}