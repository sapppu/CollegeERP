public class CurrentClass extends Thread {
    public static void main(String[] args) {
        Thread t = new Thread(this);
        System.out.println("Current Thread"+t.CurrentThread());
    }
}
