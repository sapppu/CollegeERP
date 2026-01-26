class AtoZ extends Thread
{
    @Override
    public void run() {
        for(int i =0;i<5;i++)
        {
            for(int j= 65; j < 91;j++)
            {
                System.out.printf("\n %c",j);
            }
        }
    }
}
class fibonacci extends Thread
{
    @Override
    public void run() {
        int a =0, b = 1,c;
        System.out.printf("\n Fibonacci series %d %d",a,b);
        for(int i=0;i<13;i++)
        {
            c = a+b;
            a=b;
            b=c;
            System.out.printf("\n Fibonacci series: %d",c);
        }
    }
}
public class MultithreadingAtoZ {
    public static void main(String[] args) {
        AtoZ t1 = new AtoZ();
        t1.start();
        fibonacci t2 = new fibonacci();
        t2.start();
    }
}
