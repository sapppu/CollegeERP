import java.util.Scanner;

class InvalidEmailException extends Exception {
    InvalidEmailException(String msg) {
        super(msg);
    }
}

public class EmailEx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        try {
            if (Character.isDigit(email.charAt(0)) || !email.contains("@"))
                throw new InvalidEmailException("Invalid Email: Must not start with a digit and must contain '@'.");
            System.out.println("Valid Email: " + email);
        } catch (InvalidEmailException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
