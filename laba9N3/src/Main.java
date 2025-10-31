import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void sortWorkers(ArrayList<Worker> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                Worker a = list.get(j);
                Worker b = list.get(j + 1);

                if (a.salary < b.salary) {
                    continue;
                } else if (a.salary > b.salary) {
                    list.set(j, b);
                    list.set(j + 1, a);
                } else {

                    String surnameA = a.name.split("\\s+")[0];
                    String surnameB = b.name.split("\\s+")[0];

                    if (surnameA.compareTo(surnameB) < 0 ){
                        list.set(j, b);
                        list.set(j + 1, a);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Worker> workers = new ArrayList<>();

        workers.add(new Worker(1, "Колотов Денис", 8000.0));
        workers.add(new Worker(2, "Лепешов Максим", 50 * 160));
        workers.add(new Worker(3, "Суходольский Илья", 9000.0));
        workers.add(new Worker(4, "Жешко Вадим", 60 * 150));
        workers.add(new Worker(5, "Илюкевич Арсений", 7000.0));
        workers.add(new Worker(6, "Новик Никита", 40 * 180));
        workers.add(new Worker(7, "Жигелев Артур", 7500.0));

        sortWorkers(workers);

        System.out.println("Все сотрудники:");
        System.out.printf("%-5s | %-20s | %-10s%n", "ID", "Имя", "Зарплата");
        System.out.println("______________________________________");
        for (int i = 0; i < workers.size(); i++) {
            Worker w = workers.get(i);
            System.out.printf("%-5d | %-20s | %-10.2f%n", w.id, w.name ,w.salary);
        }

        System.out.println("\nПервые 5 имён:");
        for (int i = 0; i < 5 && i < workers.size(); i++) {
            System.out.println(workers.get(i).name);
        }

        System.out.println("\nПоследние 3 ID:");
        int n = workers.size();
        for (int i = n - 3; i < n; i++) {
            if (i >= 0) {
                System.out.println(workers.get(i).id);
            }
        }

        System.out.println("\n" + "=".repeat(30));

        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Иванов И.И.", 75000.0888888);
        staff[1] = new Employee("Петрова А.В.", 82500.50);
        staff[2] = new Employee("Сидоров К.С.", 68000.75);

        Report.generateReport(staff);

    }
}