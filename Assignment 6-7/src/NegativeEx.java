import java.util.Scanner;

class NegativeValueException extends Exception {
    NegativeValueException(String msg) {
        super(msg);
    }
}

public class NegativeEx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        try {
            if (n < 0)
                throw new NegativeValueException("Negative Value Entered!");
            System.out.println("Valid Number: " + n);
        } catch (NegativeValueException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
