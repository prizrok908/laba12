import java.util.ArrayList;

public class Main {

    public static void processStudents(ArrayList<Student> students) {
        for (int i = students.size() - 1; i >= 0; i--) {
            Student s = students.get(i);
            if (s.getAverage() < 3.0) {
                students.remove(i);
            } else {
                s.course = s.course + 1;
            }
        }
    }

    public static void printStudents(ArrayList<Student> students, int course) {
        System.out.println("Студенты на курсе " + course + ":");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).course == course) {
                System.out.println(students.get(i).name);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        
        students.add(new Student("Денис","Т-319",4, new ArrayList<Double>(){{
            add(4.5);add(2.0);add(3.0);
        }}));
        students.add(new Student("Максим","n-345",3, new ArrayList<Double>(){{
            add(4.5);add(4.0);add(3.0);
        }}));
        students.add(new Student("Вадим","n-345",1, new ArrayList<Double>(){{
            add(1.5);add(2.0);add(2.0);
        }}));


        System.out.println("До:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).name + " — курс " + students.get(i).course +
                    ", средний: " + students.get(i).getAverage());
        }

        processStudents(students);

        System.out.println("\nПосле:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).name + " — курс " + students.get(i).course +
                    ", средний: " + students.get(i).getAverage());
        }

        printStudents(students, 3);
    }
}