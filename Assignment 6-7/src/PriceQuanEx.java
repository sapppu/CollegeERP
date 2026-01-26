import java.util.Scanner;

class QuantityException extends Exception {
    QuantityException(String msg) { super(msg); }
}

class PriceException extends Exception {
    PriceException(String msg) { super(msg); }
}

class Item {
    int itemCode;
    String description;
    int quantity;
    double rate;

    void accept() throws QuantityException, PriceException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Item Code: ");
        itemCode = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Description: ");
        description = sc.nextLine();
        System.out.print("Enter Quantity: ");
        quantity = sc.nextInt();
        System.out.print("Enter Rate: ");
        rate = sc.nextDouble();

        if (quantity <= 0)
            throw new QuantityException("Quantity must be greater than zero!");
        if (rate <= 0)
            throw new PriceException("Price must be greater than zero!");
    }

    void display() {
        System.out.println("\nItem Code: " + itemCode);
        System.out.println("Description: " + description);
        System.out.println("Quantity: " + quantity);
        System.out.println("Rate: " + rate);
    }

    public static void main(String[] args) {
        Item items[] = new Item[5];
        for (int i = 0; i < 5; i++) {
            items[i] = new Item();
            try {
                System.out.println("\nEnter details for Item " + (i + 1));
                items[i].accept();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n--- Accepted Item Details ---");
        for (Item item : items)
            item.display();
    }
}
