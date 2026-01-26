// Printer class (COMMON LOCK OBJECT)
class Printer {

    // synchronized method for alphabet printing
    synchronized void printAlphabet() {
        for (int i = 0; i < 2; i++) {
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                System.out.println("Alphabet : " + ch);
            }
        }
    }

    // synchronized method for Fibonacci series printing
    synchronized void printFibo() {
        int a = 0, b = 1, c;
        for (int i = 0; i < 16; i++) {
            System.out.println("Fibonacci : " + a);
            c = a + b;
            a = b;
            b = c;
        }
    }
}

// Thread class 1 — A2Z
class A2Z extends Thread {

    Printer p;

    A2Z(Printer p) {
        this.p = p;
    }

    @Override
    public void run() {
        p.printAlphabet(); // synchronized method
    }
}

// Thread class 2 — Fibonacci
class Fibonacci extends Thread {

    Printer p;

    Fibonacci(Printer p) {
        this.p = p;
    }

    @Override
    public void run() {
        p.printFibo(); // synchronized method
    }
}

// MAIN CLASS
public class multithread2 {

    public static void main(String[] args) {

        Printer p = new Printer(); // COMMON LOCK OBJECT

        A2Z t1 = new A2Z(p);
        Fibonacci t2 = new Fibonacci(p);

        t1.start();
        t2.start();
    }
}

