class TotalEarning extends Thread {
    int t = 0;

    public void run() {
        synchronized (this) {
            for (int i = 1; i <= 10; i++) {
                t = t + 100;
            }
            this.notify();
        }
    }
}

public class moviebook {
    public static void main(String[] args) {
        TotalEarning te = new TotalEarning();
        te.start();
        synchronized (te){
            try {
                te.wait();
                System.out.println("Total : "+te.t);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
