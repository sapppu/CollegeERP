import java.util.Scanner;
public class GoodBye {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        int count = 0;

        System.out.println("Enter lines (type 'good bye' to stop):");

        while (true) {
            line = sc.nextLine();
            if (line.equalsIgnoreCase("good bye"))
                break;

            if (line.contains("India") || line.contains("Hello")) {
                System.out.println("Matched Line: " + line);
                count++;
            }
        }

        System.out.println("\nTotal lines containing 'India' or 'Hello': " + count);
    }
}
