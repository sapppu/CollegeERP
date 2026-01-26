

import java.util.Scanner;

abstract class Order {
    int id;
    String description;

    abstract void accept();
    abstract void display();
}

class PurchaseOrder extends Order {
    String customerName;

    void accept() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Purchase Order ID: ");
        id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Description: ");
        description = sc.nextLine();
        System.out.print("Enter Customer Name: ");
        customerName = sc.nextLine();
    }

    void display() {
        System.out.println("\nPurchase Order ID: " + id);
        System.out.println("Description: " + description);
        System.out.println("Customer Name: " + customerName);
    }
}

class SalesOrder extends Order {
    String vendorName;

    void accept() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Sales Order ID: ");
        id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Description: ");
        description = sc.nextLine();
        System.out.print("Enter Vendor Name: ");
        vendorName = sc.nextLine();
    }

    void display() {
        System.out.println("\nSales Order ID: " + id);
        System.out.println("Description: " + description);
        System.out.println("Vendor Name: " + vendorName);
    }
}

public class OrderDisp {
    public static void main(String[] args) {
        PurchaseOrder po[] = new PurchaseOrder[5];
        SalesOrder so[] = new SalesOrder[5];
        for (int i = 0; i < 5; i++) {
            po[i] = new PurchaseOrder();
            System.out.println("\nEnter details for Purchase Order " + (i + 1));
            po[i].accept();
        }
        for (int i = 0; i < 5; i++) {
            so[i] = new SalesOrder();
            System.out.println("\nEnter details for Sales Order " + (i + 1));
            so[i].accept();
        }

        for (PurchaseOrder p : po) p.display();
        for (SalesOrder s : so) s.display();
    }
}
