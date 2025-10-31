import java.io.*;
import java.util.Scanner;

public class Task2 {
    public static void run(Scanner scanner) {
        String file = "temperatures.txt";

        try (PrintWriter w = new PrintWriter(new FileWriter(file))) {
            for (int i = 0; i < 15; i++) {
                System.out.print("Температура " + (i + 1) + ": ");
                w.println(scanner.nextDouble());
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
            return;
        }

        double sum = 0;
        int count = 0;
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = r.readLine()) != null) {
                sum += Double.parseDouble(line.trim());
                count++;
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
            return;
        }

        System.out.printf("Средняя температура: %.2f\n", sum / count);
    }
}