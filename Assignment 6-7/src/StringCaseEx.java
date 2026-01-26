class UpperCaseException extends Exception {
    UpperCaseException(String msg) {
        super(msg);
    }
}

public class StringCaseEx {
    public static void main(String[] args) {
        try {
            String input = args[0];
            if (input.equals(input.toUpperCase()))
                throw new UpperCaseException("String is in uppercase!");
            System.out.println("Valid String: " + input);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a string argument.");
        } catch (UpperCaseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
