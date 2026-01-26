import java.util.Scanner;
class PrintPrime extends Thread{
    Scanner sc = new Scanner(System.in);
    public void run(){
        System.out.println("enter the number");
        int n = sc.nextInt();
        int cnt = 0;
        int sum = 0;
        for (cnt = 0; cnt < 100; n++) {
            boolean isPrime = true;
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime && n > 1) {
                sum += n;
                cnt++;
                System.out.print(n+",");
            }
    }
}

//class VowelPrint extends Thread{
//    Scanner sc = new Scanner(System.in);
//    public void run(){
//        System.out.println("Enter The string");
//        String s = sc.next();
//    }
//    }
//}

public class multithreadEx1 {
    public static void main(String[] args) {
        PrintPrime p = new PrintPrime();
        p.start();
    }
}
}
