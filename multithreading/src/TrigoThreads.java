import java.util.*;

class SinThread extends Thread {
    double degree, result;

    SinThread(double degree) {
        this.degree = degree;
    }

    public void run() {
        double rad = Math.toRadians(degree);
        result = Math.sin(rad);
        System.out.println("Sin(" + degree + ") = " + result);
    }
}

class CosThread extends Thread {
    double degree, result;

    CosThread(double degree) {
        this.degree = degree;
    }

    public void run() {
        double rad = Math.toRadians(degree);
        result = Math.cos(rad);
        System.out.println("Cos(" + degree + ") = " + result);
    }
}

class TanThread extends Thread {
    double degree, result;

    TanThread(double degree) {
        this.degree = degree;
    }

    public void run() {
        double rad = Math.toRadians(degree);
        result = Math.tan(rad);
        System.out.println("Tan(" + degree + ") = " + result);
    }
}

public class TrigoThreads {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter angle in degrees: ");
        double degree = sc.nextDouble();

        SinThread t1 = new SinThread(degree);
        CosThread t2 = new CosThread(degree);
        TanThread t3 = new TanThread(degree);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }
}
