import java.util.Scanner;

class table1
{
    synchronized void display()
    {
        Scanner s = new Scanner(System.in);
        System.out.printf("\n Enter number:");
        int n = s.nextInt();
        for(int i=1;i<=10;i++)
        {
            System.out.printf("\n %d * %d = %d",n,i,n*i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class
print1 implements Runnable
{
    table1 t;
    print1(table1 t)
    {
        this.t = t;
    }

    @Override
    public void run() {
        t.display();
    }
}

//class print2 implements Runnable
//{
//    table1 t;
//    print2(table1 t)
//    {
//        this.t = t;
//    }
//
//    @Override
//    public void run() {
//        t.display();
//    }
//}

public class SynchronizedMethod {
    public static void main(String[] args) {
        table1 obj = new table1();
        print1 p1 = new print1(obj);
        //print2 p2 = new print2(obj);
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p1);
        t1.start();
        t2.start();
    }
}
