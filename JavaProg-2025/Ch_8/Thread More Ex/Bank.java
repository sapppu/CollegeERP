import java.util.Scanner;
class Customer extends Thread{
Scanner sc = new Scanner(System.in);
String name;
double balance;
    public Customer(String name,double bal) {
    this.name=name;
    this.balance=bal;
    }
@Override
public synchronized  void run(){
    try {
        boolean b = true;
        while (b) { 
            System.out.println("Welcome "+name+"WHAT DO YOU WANT TO DO?:");
        System.out.println("1.Deposit 2.Withdraw 3.Display Details");
        int choice = sc.nextInt();
    switch (choice) {
        case 1:
            System.out.println("Enter the Amount to Deposit:");
            double amt1 = sc.nextDouble();
            deposit(amt1);
        case 2:
            System.out.println("Enter the Amount to Withdraw");
            double w = sc.nextDouble();
            withdraw(w);
        case 3:
            System.out.println("Account Details :\n Name :"+name+"\n Balance :"+balance);
        case 4:
            b=false;
            System.out.println("eXITING!");
            break;
    }
   }
    } catch (Exception e) {
    System.out.println(e);
    }
finally{
sc.close();
}
}
synchronized  void withdraw(double amt){
    if(amt > balance || balance == 0.0){
        System.out.println("Sorry! You seem to have insufficent balance therrfore cannot withdraw"+amt+"from account");
    }
    else{
        balance -= amt;
        System.out.println("Amount "+amt+"Withdrawn for ACCOUNT:");
    }
}
synchronized  void deposit(double amt){
    balance += amt; 
    System.out.println("Deposited "+amt+"IN ACCOUNT");
}
}

class Bank{
    public static void main(String[] args) {
        Customer c1 = new Customer("ABC",100000.00);
        Customer c2 = new Customer("DEF",200000.00);
        Customer c3 = new Customer("XYZ",100000.00);
        c1.start();
        c2.start();
        c3.start();
        
    }
}