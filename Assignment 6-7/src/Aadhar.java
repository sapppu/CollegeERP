import java.util.Scanner;

class Voter {
    String voterName, voterAddress, voterAadhar, voterContact;

    void accept() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Voter Name: ");
        voterName = sc.nextLine();
        System.out.print("Enter Address: ");
        voterAddress = sc.nextLine();
        System.out.print("Enter Contact No: ");
        voterContact = sc.nextLine();
        System.out.print("Enter Aadhar No: ");
        voterAadhar = sc.nextLine();
    }

    void display() {
        System.out.println("\nName: " + voterName);
        System.out.println("Address: " + voterAddress);
        System.out.println("Contact: " + voterContact);
        System.out.println("Aadhar: " + voterAadhar);
    }

    public static void main(String[] args) {
        Voter v[] = new Voter[10];
        for (int i = 0; i < 10; i++) {
            v[i] = new Voter();
            System.out.println("\nEnter details for voter " + (i + 1));
            v[i].accept();
        }
        for (Voter voter : v) {
            voter.display();
        }
    }
}
