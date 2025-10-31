import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите задание:");
        System.out.println("1 — Информация о себе");
        System.out.println("2 — Средняя температура");
        System.out.println("3 — Уникальность имён");
        System.out.print("Ваш выбор: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // съесть \n

        switch (choice) {
            case 1:
                Task1.run();
                break;
            case 2:
                Task2.run(scanner);
                break;
            case 3:
                Task3.run(scanner);
                break;
            default:
                System.out.println("Неверный выбор.");
        }
        scanner.close();
    }
}