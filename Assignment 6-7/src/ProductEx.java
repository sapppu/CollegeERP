import java.util.Scanner;

class InvalidProductException extends Exception {
    InvalidProductException(String msg) {
        super(msg);
    }
}

class Product {
    int productCode;
    String productName;
    double weight;

    void accept() throws InvalidProductException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Product Code: ");
        productCode = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Product Name: ");
        productName = sc.nextLine();
        System.out.print("Enter Product Weight: ");
        weight = sc.nextDouble();

        if (weight > 100)
            throw new InvalidProductException("Invalid Product: Weight cannot exceed 100!");
    }

    void display() {
        System.out.println("\nProduct Code: " + productCode);
        System.out.println("Product Name: " + productName);
        System.out.println("Weight: " + weight);
    }

    public static void main(String[] args) {
        Product p = new Product();
        try {
            p.accept();
            p.display();
        } catch (InvalidProductException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
