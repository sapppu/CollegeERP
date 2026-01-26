import java.util.Scanner;
public class Cmp {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter  first number:");
        int a = s.nextInt();
        System.out.println("Enter  second number:");
        int b = s.nextInt();
        if (a > b)
            System.out.println("a is greater than b");
        else
            System.out.println("a is less than b");
    }
}
