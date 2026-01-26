import java.util.Scanner;
public class D2B {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter  number:");
        int d = s.nextInt();
        System.out.println("Binary: " + Integer.toBinaryString(d));
    }
}
