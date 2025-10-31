public class Main {
    public static void main(String[] args) {
        System.out.println("=== Задание 1: Класс Мышь ===");
        Mouse mickey = new Mouse("Микки", 2, "чёрный", "сыр");
        Mouse jerry = new Mouse();

        mickey.info();
        mickey.squeak();
        mickey.eat();
        mickey.rest();
        mickey.hide();

        System.out.println("\n---\n");

        jerry.setAge(1);
        jerry.info();
        jerry.squeak();

        System.out.println("\n=== Задание 2: Одномерный массив ===");
        Array arr1 = new Array(new double[]{1, 2, 3});
        Array arr2 = new Array(new double[]{4, 5, 6});

        System.out.println("Массив 1:");
        arr1.print();

        System.out.println("Массив 2:");
        arr2.print();

        Array sum = arr1.add(arr2);
        Array diff = arr1.subtract(arr2);
        Array prod = arr1.multiply(arr2);

        System.out.println("\nСумма:");
        sum.print();

        System.out.println("Разность:");
        diff.print();

        System.out.println("Произведение:");
        prod.print();

        arr1.set(0, 10);
        System.out.println("\nПосле изменения arr1[0] = 10:");
        arr1.print();
    }
}