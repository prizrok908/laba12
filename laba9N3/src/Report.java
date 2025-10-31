public class Report {
    public static void generateReport(Employee[] employees) {
        System.out.println("Отчёт по зарплатам:");
        for (int i = 0; i < employees.length; i++) {
            String name = employees[i].fullname;
            String lastName = name;
            if (name.indexOf(" ") != -1) {
                lastName = name.substring(0, name.indexOf(" "));
            }

            while (lastName.length() < 15) {
                lastName = lastName + " ";
            }
            if (lastName.length() > 15) {
                lastName = lastName.substring(0, 15);
            }

            double sal = Math.round(employees[i].salary * 100.0) / 100.0;
            String salStr = sal + "";
            if (salStr.indexOf(".") == -1) {
                salStr = salStr + ".00";
            } else {
                String[] parts = salStr.split("\\.");
                if (parts[1].length() == 1) {
                    salStr = parts[0] + "." + parts[1] + "0";
                }
            }

            while (salStr.length() < 10) {
                salStr = " " + salStr;
            }

            System.out.println(lastName + salStr);
        }
    }
}