import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FunctionClass {
    public static void main(String[] args) {
        Student first_student  = new Student();
        Student second_student = new Student(4.50, "22222", "Petyr Petrov");
        Student third_student = new Student(4.50, "33333", "Zahari Zahariev");
        Student fourth_student = new Student(4.50, "33333", "Aleksi Aleksiev");
        Student fifth_student = new Student(4.50, "33333", "Stoicho Stoichev");

        List<Student> students = new ArrayList<Student>(5);

        students.add(first_student);
        students.add(second_student);
        students.add(third_student);
        students.add(fourth_student);
        students.add(fifth_student);

        Collections.sort(students);

        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}
