import java.io.*;
import java.util.*;

public class Task3 {
    public static void run(Scanner scanner) {
        String file = "names.txt";
        List<String> names = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        try (PrintWriter w = new PrintWriter(new FileWriter(file))) {
            System.out.println("Введите 5 имён:");
            for (int i = 0; i < 5; i++) {
                String name = scanner.nextLine().trim();
                while (name.isEmpty()) {
                    System.out.print("Имя не может быть пустым: ");
                    name = scanner.nextLine().trim();
                }
                w.println(name);
                names.add(name);
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
            return;
        }

        System.out.println("\nПроверка уникальности:");
        for (String name : names) {
            if (!seen.add(name)) {
                System.out.println(name + " — дубликат!");
            } else {
                System.out.println(name + " — уникально.");
            }
        }
    }
}