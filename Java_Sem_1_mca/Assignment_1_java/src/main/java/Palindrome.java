import java.util.Scanner;
public class Palindrome {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter  number:");
        int n = s.nextInt();
        int orig = n;
        int rev = 0;
        while (n != 0) {
            int digit = n % 10;
            rev = rev * 10 + digit;
            n /= 10;
        }
        if (orig == rev) {
            System.out.println(orig + " is a palindrome.");
        } else {
            System.out.println(orig + " is not a palindrome.");
        }
    }
}
