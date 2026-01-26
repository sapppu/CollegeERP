import java.util.*;

class Staff {
    String name;
    String bd;
    String desg;
    double sal;

    Staff(String name, String bd, String desg, double sal) {
        this.name = name;
        this.bd = bd;
        this.desg = desg;
        this.sal = sal;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + bd);
        System.out.println("Designation: " + desg);
        System.out.println("Salary: " + sal);
        System.out.println("-------------------");
    }
}

public class StaffDet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter no of Entries you want: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        Staff[] staff = new Staff[n];

        // Input staff details
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details of Staff " + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Birthdate: ");
            String bd = sc.nextLine();

            System.out.print("Designation: ");
            String desg = sc.nextLine();

            System.out.print("Salary: ");
            double sal = sc.nextDouble();
            sc.nextLine(); // consume newline

            staff[i] = new Staff(name, bd, desg, sal);
            System.out.println();
        }

        // Display staff details
        System.out.println("Staff Details:");
        for (int i = 0; i < n; i++) {
            staff[i].display();
        }

        // Find max salary staff
        Staff max = staff[0];
        Staff max2 = staff[0];
        for (int i = 1; i < n; i++) {
            if (staff[i].sal > max.sal) {
                max = staff[i];
                if(staff[i].sal > max.sal && max2.sal<max.sal) {
                    max2 = staff[i];
                }
            }
        }
        System.out.println("Staff with max salary: " + max.name + " (" + max.sal + ")");
        System.out.println("Staff with second max salary: " + max2.name + " (" + max2.sal + ")");
    }
}
