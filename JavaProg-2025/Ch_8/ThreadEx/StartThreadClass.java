import java.lang.*;
class ThreadExample extends Thread
{
	public ThreadExample(String name)
	{
		super(name);   /*  call base class constructor i.e 
				   public Thread(String threadName) */
	}

	public void run()
	{
		System.out.println(Thread.currentThread());
		for(int i=0;i<=5;i++)
		   System.out.println(i);
	}

}

public class StartThreadClass
{
	public static void main(String args[])
	{
		ThreadExample t1=new ThreadExample("First");
		t1.start();
		System.out.println("This is :"+Thread.currentThread());
	}
}