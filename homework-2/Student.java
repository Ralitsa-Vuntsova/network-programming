public class Student implements Comparable<Student> {
    private double averageGrade;
    private String facultyNumber;
    private String name;

    public Student() {
        this.averageGrade = 6.00;
        this.facultyNumber = "11111";
        this.name = "Ivan Ivanov";
    }

    public Student(double averageGrade, String facultyNumber, String name) {
        this.averageGrade = averageGrade;
        this.facultyNumber = facultyNumber;
        this.name = name;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student student) {
        if (this.averageGrade == student.averageGrade) {
            return this.name.compareTo(student.name);
        }
        else if (this.averageGrade < student.averageGrade) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
