class print_statement extends Thread {

    String s;
    Thread t;

    print_statement(String s)
    {
        t = new Thread(this,s);
        t.start();
    }
    @Override
    public void run() {
        for(int i=0;i<5;i++)
        {
            System.out.printf("\n %s",t.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
class print_statement2 implements Runnable {

    String s;
    Thread t;

    print_statement2(String s)
    {
        t = new Thread(this,s);
        t.start();
    }
    @Override
    public void run() {
        for(int i=0;i<5;i++)
        {
            System.out.printf("\n %s",t.getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
class print_statement3 implements Runnable {

    String s;
    Thread t;

    print_statement3(String s)
    {
        t = new Thread(this,s);
        t.start();
    }
    @Override
    public void run() {
        for(int i=0;i<5;i++)
        {
            System.out.printf("\n %s",t.getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class Interthreadcommunication {
    public static void main(String[] args) {
        print_statement ps = new print_statement("Hello!!");
        ps.start();
        print_statement2 ps1 = new print_statement2("Wear Mask!!");
        print_statement3 ps2 = new print_statement3("Use Sanitizer!!");

    }
}
