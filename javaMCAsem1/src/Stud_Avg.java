import java.util.Scanner;
class Student {
    int PRN;
    String name;
    int m1, m2;

    public Student(int PRN, String name, int m1, int m2) {
        this.PRN = PRN;
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
    }

    public double Cal_Avg() {
        return (double) (m1 + m2) / 2.0;
    }
}

public class Stud_Avg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] st = new Student[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter Details " + (i + 1) + ":");
            System.out.print("PRN: ");
            int PRN = sc.nextInt();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("m1: ");
            int m1 = sc.nextInt();

            System.out.print("m2: ");
            int m2 = sc.nextInt();

            st[i] = new Student(PRN, name, m1, m2);
        }

        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (st[i].Cal_Avg() < st[j].Cal_Avg()) {
                    Student t = st[i];
                    st[i] = st[j];
                    st[j] = t;
                }
            }
        }

        System.out.println("Top 2 students: ");
        for (int i = 0; i < 2; i++) {
            System.out.println("PRN : " + st[i].PRN);
            System.out.println("Name : " + st[i].name);
            System.out.println("Average : " + st[i].Cal_Avg());
        }
    }
}