 public class primeno_cmdlln {

        public static boolean isPrime(int num) {
            if (num <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            if (args.length == 0) {
                System.out.println("No input provided.");
                return;
            }

            System.out.print("Prime numbers: ");
            for (String arg : args) {
                try {
                    int num = Integer.parseInt(arg);
                    if (isPrime(num)) {
                        System.out.print(num + " ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(arg + " is not a valid number.");
                }
            }
        }
    }
