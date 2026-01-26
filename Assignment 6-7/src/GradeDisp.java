interface CalculateResult {
    void calculateTotal();
    void calculatePercentage();
    void calculateGrade();
}

//abstract class CalRs{
//    void calculateTotal();
//    void calculatePercentage();
//    void calculateGrade();
//}

class Student implements CalculateResult {
    String name;
    int marks1, marks2, marks3;
    int total;
    float percentage;
    String grade;

    Student(String name, int m1, int m2, int m3) {
        this.name = name;
        this.marks1 = m1;
        this.marks2 = m2;
        this.marks3 = m3;
    }

    public void calculateTotal() {
        total = marks1 + marks2 + marks3;
    }

    public void calculatePercentage() {
        percentage = (float) total / 3;
    }

    public void calculateGrade() {
        if (percentage >= 75) grade = "A";
        else if (percentage >= 60) grade = "B";
        else if (percentage >= 50) grade = "C";
        else grade = "Fail";
    }

    void display() {
        System.out.println("\nStudent Name: " + name);
        System.out.println("Total Marks: " + total);
        System.out.println("Percentage: " + percentage);
        System.out.println("Grade: " + grade);
    }

    public static void main(String[] args) {
        Student s = new Student("Swapnaj", 78, 85, 69);
        s.calculateTotal();
        s.calculatePercentage();
        s.calculateGrade();
        s.display();
    }
}
