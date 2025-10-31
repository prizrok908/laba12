package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Алексей");

        Tester t1 = new Tester("Иван", "Петров", 5, "B2", 120000);
        Tester t2 = new Tester("Мария", "Сидорова", 2, "C1");
        Tester t3 = new Tester("Анна", "Кузнецова");

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);

        t1.increaseSalary(10000);
        t1.increaseSalary(10.0, true);
        t1.increaseSalary(5000, "за успешный проект");

        String title = Tester.getJobTitle();
        System.out.println("Должность: " + title);
    }
}