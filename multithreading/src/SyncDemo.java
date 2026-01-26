class Bank {
    private int balance = 0;

    public synchronized void deposit(int amount) {
        balance = balance + amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + " | Balance: " + balance);
    }

    public void depositUsingBlock(int amount) {
        synchronized(this) {
            balance = balance + amount;
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + " using block | Balance: " + balance);
        }
    }
}

class MyThread extends Thread {
    Bank bank;

    MyThread(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        bank.deposit(100);
        bank.depositUsingBlock(200);
    }
}

public class SyncDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        MyThread t1 = new MyThread(bank);
        MyThread t2 = new MyThread(bank);

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();
    }
}
