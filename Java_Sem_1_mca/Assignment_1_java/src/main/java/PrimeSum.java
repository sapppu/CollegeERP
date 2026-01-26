public class PrimeSum {
    public static void main(String[] args) {
        int cnt = 0;
        int n = 2;
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
        System.out.println("Sum of the first 100 prime numbers: " + sum);
    }
}
