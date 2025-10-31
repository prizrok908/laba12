import java.util.ArrayList;

public class Student {
    public String name;
    public String group;
    public int course;
    public ArrayList<Double> grades;

    public Student(String name, String group, int course, ArrayList<Double> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public double getAverage() {
        double sum = 0;
        for (int i = 0; i < grades.size(); i++) {
            sum = sum + grades.get(i);
        }
        return sum / grades.size();
    }
}