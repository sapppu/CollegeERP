import java.io.*;
class ThreadExample implements Runnable
{
	class_A obj1;
	Thread t;
	ThreadExample(class_A c)	
	{
		obj1=c;
		t=new Thread(this);
	}
	public void run()
	{
		obj1.printValue();
	}
	public static void main(String args[])
	{
		class_A ca = new class_A();
		ThreadExample one = new ThreadExample(ca);
		one.t.start();
		
		ThreadExample two = new ThreadExample(ca);
		two.t.start();

		ThreadExample three = new ThreadExample(ca);
		three.t.start();
	}
}
class class_A 
{
	synchronized void printValue()
	{
		try
		{
			for(int i=0;i<=5;i++)
			{
				System.out.println(i+" ");
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e)
		{
			System.out.println(e);
		}
	}
}

