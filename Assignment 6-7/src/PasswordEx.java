class InvalidPasswordException extends Exception {
    InvalidPasswordException(String msg) {
        super(msg);
    }
}

public class PasswordEx{
    public static void main(String[] args) {
        String username = "Swapnaj";
        String password = "SappuDon";

        try {
            if (!username.equals(password))
                throw new InvalidPasswordException("Invalid Password: Username and password must match!");
            System.out.println("Login Successful!");
        } catch (InvalidPasswordException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
