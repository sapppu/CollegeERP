class TablePrinter {

    synchronized void printTable(int n) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " table : " + (n * i));
        }
    }
}

class TableThread1 extends Thread {
    TablePrinter tp;
    int number;

    TableThread1(TablePrinter tp, int number) {
        this.tp = tp;
        this.number = number;
    }

    @Override
    public void run() {
        tp.printTable(number);   // synchronized method
    }
}

public class TableSynchroMeth {
    public static void main(String[] args) {

        TablePrinter tp = new TablePrinter();

        TableThread1 t1 = new TableThread1(tp, 5);
        TableThread1 t2 = new TableThread1(tp, 7);
        TableThread1 t3 = new TableThread1(tp, 13);

        t1.start();
        t2.start();
        t3.start();
    }
}
