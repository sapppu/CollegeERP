import java.util.Scanner;

class Passenger {
    int passengerId;
    String name;
    int age;
    String gender;
    String source;
    String destination;

    void accept() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Passenger ID: ");
        passengerId = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Age: ");
        age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Gender: ");
        gender = sc.nextLine();
        System.out.print("Enter Source: ");
        source = sc.nextLine();
        System.out.print("Enter Destination: ");
        destination = sc.nextLine();
    }

    void display() {
        System.out.println("\nPassenger ID: " + passengerId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
    }
}

public class PassengerDet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Passenger[] passengers = new Passenger[10];

        System.out.println("=== Enter Details of 10 Passengers ===");
        for (int i = 0; i < 10; i++) {
            passengers[i] = new Passenger();
            System.out.println("\nEnter details for Passenger " + (i + 1));
            passengers[i].accept();
        }

        for (int i = 0; i < 10; i++) {
            passengers[i].display();
        }
    }
}
