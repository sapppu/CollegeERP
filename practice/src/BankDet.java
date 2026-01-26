import java.util.Scanner;

class Bank {
    String custName;
    int acNo;
    double balance;

    Bank(String custName, int acNo, double balance) {
        this.custName = custName;
        this.acNo = acNo;
        this.balance = balance;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
        System.out.println("Updated Balance: " + balance);
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
        System.out.println("Updated Balance: " + balance);
    }

    void display() {
        System.out.println("Customer Name: " + custName);
        System.out.println("Account No: " + acNo);
        System.out.println("Balance: " + balance);
    }
}

public class BankDet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        int acNo = sc.nextInt();

        System.out.print("Enter Initial Balance: ");
        double bal = sc.nextDouble();

        Bank b1 = new Bank(name, acNo, bal);

        b1.display();

        System.out.print("Enter amount to deposit: ");
        double dep = sc.nextDouble();
        b1.deposit(dep);

        System.out.print("Enter amount to withdraw: ");
        double wd = sc.nextDouble();
        b1.withdraw(wd);

        System.out.println("Final Balance: " + b1.balance);
    }
}
