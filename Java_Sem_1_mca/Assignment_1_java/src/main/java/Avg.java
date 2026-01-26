import java.util.Scanner;
public class Avg {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        double sum = 0;
        for (int i = 1; i <= 5; i++) {
            System.out.println("Enter number " + i + ":");
            sum += c.nextDouble();
        }
        System.out.println("Average: " + (sum / 5));
    }
}
