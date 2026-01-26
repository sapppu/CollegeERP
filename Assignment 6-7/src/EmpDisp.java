//import java.util.Scanner;

import java.util.Scanner;
abstract class Employee {
    abstract void accept();
    abstract void display();
}

class Manager extends Employee {
    int mid;
    String mname;
    String phno;

    void accept() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Manager ID: ");
        mid = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Manager Name: ");
        mname = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        phno = sc.nextLine();
    }

    void display() {
        System.out.println("\n--- Manager Details ---");
        System.out.println("Manager ID: " + mid);
        System.out.println("Manager Name: " + mname);
        System.out.println("Phone Number: " + phno);
    }
}

class Worker extends Employee {
    String name;
    int working_hours;

    void accept() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Worker Name: ");
        name = sc.nextLine();
        System.out.print("Enter Working Hours: ");
        working_hours = sc.nextInt();
    }

    void display() {
        System.out.println("\n--- Worker Details ---");
        System.out.println("Worker Name: " + name);
        System.out.println("Working Hours: " + working_hours);
    }
}

public class EmpDisp {
    public static void main(String[] args) {
        Manager m = new Manager();
        Worker w = new Worker();
        m.accept();
        w.accept();
        m.display();
        w.display();
    }
}
