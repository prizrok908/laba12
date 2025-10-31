import java.io.*;

public class Task1 {
    public static void run() {
        String filename = "about_me.txt";
        String info = "Колотов Денис Павлович";

        try (PrintWriter w = new PrintWriter(new FileWriter(filename))) {
            w.println(info);
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
            return;
        }

        File f = new File(filename);
        System.out.println("\nФайл: " + f.getName());
        System.out.println("Размер: " + f.length() + " байт");
        System.out.println("Существует: " + f.exists());

        try (BufferedReader r = new BufferedReader(new FileReader(filename))) {
            System.out.println("Содержимое: " + r.readLine());
        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }
    }
}