import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("\n Enter any five numbers:");
        float a = sc.nextInt();
        float b = sc.nextInt();
        float c = sc.nextInt();
        float d = sc.nextInt();
        float e = sc.nextInt();
        System.out.printf("\n Average of five number is: %f", (a+b+c+d+e)/5);
    }
}
