import java.util.Scanner;
public class B2D {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a binary number:");
        String binary = s.nextLine();
        System.out.println("Decimal: " + Integer.parseInt(binary, 2));
    }
}
