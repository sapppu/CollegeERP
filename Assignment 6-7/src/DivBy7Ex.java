class NotDivisibleBy7Exception extends Exception {
    NotDivisibleBy7Exception(String msg) {
        super(msg);
    }
}

public class DivBy7Ex {
    public static void main(String[] args) {
        try {
            int num = Integer.parseInt(args[0]);
            if (num % 7 != 0)
                throw new NotDivisibleBy7Exception("Number is not divisible by 7!");
            System.out.println(num + " is divisible by 7.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a number as command line argument.");
        } catch (NotDivisibleBy7Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
