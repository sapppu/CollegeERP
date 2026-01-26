public class EvenOdd {
    public static void main(String[] args) {
        Even();
        Odd();
    }
    public static void Even() {
        for (int i = 2; i <= 20; i++) {
            if (i % 2 == 0) System.out.println(i);
        }
    }
    public static void Odd() {
        for (int i = 2; i <= 20; i++) {
            if (i % 2 != 0) System.out.println(i);
        }
    }
}