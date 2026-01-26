import java.util.Scanner;

interface CalculateResult1 {
    void calculateTotal();
    void calculatePercentage();
    void calculateGrade();
}

class ZeroMarksException extends Exception {
    ZeroMarksException(String msg) {
        super(msg);
    }
}

class Student2 implements CalculateResult1 {
    String name;
    int marks1, marks2, marks3, total;
    double percentage;
    String grade;

    Student2(String name, int m1, int m2, int m3) throws ZeroMarksException {
        if (m1 < 0 || m2 < 0 || m3 < 0)
            throw new ZeroMarksException("Marks cannot be negative!");
        this.name = name;
        this.marks1 = m1;
        this.marks2 = m2;
        this.marks3 = m3;
    }

    public void calculateTotal() { total = marks1 + marks2 + marks3; }

    public void calculatePercentage() { percentage = total / 3.0; }

    public void calculateGrade() {
        if (percentage >= 75) grade = "A";
        else if (percentage >= 60) grade = "B";
        else if (percentage >= 50) grade = "C";
        else grade = "Fail";
    }

    void display() {
        System.out.println("\nName: " + name);
        System.out.println("Total: " + total);
        System.out.println("Percentage: " + percentage);
        System.out.println("Grade: " + grade);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        Student2[] s = new Student2[n];
        for (int i = 0; i < n; i++) {
            try {
                System.out.println("\nEnter details for student " + (i + 1));
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Marks1: ");
                int m1 = sc.nextInt();
                System.out.print("Marks2: ");
                int m2 = sc.nextInt();
                System.out.print("Marks3: ");
                int m3 = sc.nextInt();
                sc.nextLine();

                s[i] = new Student2(name, m1, m2, m3);
                s[i].calculateTotal();
                s[i].calculatePercentage();
                s[i].calculateGrade();
            } catch (ZeroMarksException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        for (Student2 st : s)
            if (st != null) st.display();
    }
}
