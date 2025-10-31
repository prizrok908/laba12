import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Employee {
    String fullname;
    double salary;

    public Employee(String fullname, double salary) {
        this.fullname = fullname;
        this.salary = salary;
    }
}

class Report {
    public static void generateReport(Employee[] employees) {
        for (Employee emp : employees) {
            System.out.printf("%-15s |%10.2f%n", emp.fullname, emp.salary);
        }
    }
}

public class Main {

    public static void task1() {
        System.out.println("=== Задание 1 ===");
        String str = "Hello, World!";
        if (!str.isEmpty()) {
            char lastChar = str.charAt(str.length() - 1);
            System.out.println("Последний символ строки \"" + str + "\": " + lastChar);
        } else {
            System.out.println("Строка пуста");
        }
        System.out.println();
    }

    public static String getMiddleTwo(String str) {
        int len = str.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Длина строки должна быть чётной");
        }
        int mid = len / 2;
        return str.substring(mid - 1, mid + 1);
    }

    public static void task2() {
        System.out.println("=== Задание 2 ===");
        System.out.println("\"string\" → \"" + getMiddleTwo("string") + "\"");
        System.out.println("\"code\" → \"" + getMiddleTwo("code") + "\"");
        System.out.println("\"Practice\" → \"" + getMiddleTwo("Practice") + "\"");
        System.out.println();
    }

    public static void task3() {
        System.out.println("=== Задание 3 ===");
        Employee[] employees = {
                new Employee("Иванов И.И.", 75000.0888888),
                new Employee("Петрова А.В.", 82500.50),
                new Employee("Сидоров К.С.", 68000.75)
        };
        Report.generateReport(employees);
        System.out.println();
    }

    public static void task4() {
        System.out.println("=== Задание 4 ===");
        String text = "ааа ббб ёёё ззз ййй ААА БББ ЁЁЁ ЗЗЗ ЙЙЙ";
        Pattern pattern = Pattern.compile("[а-яА-ЯёЁ]+");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println();
    }

    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static void task5() {
        System.out.println("=== Задание 5 ===");
        String sentence = "Если есть хвосты по дз, начните с 1 не сданного задания. 123 324 111 4554";
        String[] words = sentence.split("\\s+");
        Pattern digitPattern = Pattern.compile("^\\d+$");

        boolean found = false;
        for (String word : words) {
            String cleanWord = word.replaceAll("[^\\d]", "");
            if (!cleanWord.isEmpty() && digitPattern.matcher(cleanWord).matches()) {
                if (isPalindrome(cleanWord)) {
                    System.out.println("Палиндром из цифр: " + cleanWord);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Палиндромов среди числовых слов не найдено.");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
    }
}