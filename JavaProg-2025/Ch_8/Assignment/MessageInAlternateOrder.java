class message implements Runnable
{
    String s;
    int n;
    Thread t;
    static Object lock = new Object();
    message(String s,int n)
    {
        this.s = s;
        this.n = n;
        t = new Thread(this,s);
        t.start();
    }

    @Override
    public void run() {
        for(int i=0;i<n;i++)
        {
            synchronized (lock)
            {
                System.out.printf("\n %d - %s",i,t.getName());
                lock.notify();
                try{
                    lock.wait();
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("\n Thread finished");
    }
}
public class MessageInAlternateOrder {

    public static void main(String[] args) {
        message m1 = new message("HELLO",5);
        message m2 = new message("WORLD",5);
//        Thread t1 = new Thread(m1);
//        Thread t2 = new Thread(m2);
//        t1.start();
//        t2.start();
    }
}
