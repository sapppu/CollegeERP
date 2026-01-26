// Example of Synchronized Method

import java.io.*;
class SynchronizedMethod implements Runnable
{
	class_A obj1;
	Thread t;
	SynchronizedMethod(class_A c )
	{
		obj1=c;
		t=new Thread(this);
	}
	public void run()
	{
		//synchronized(obj1) // monitor hold excluive lock on obj1 object.
		//{
			obj1.printValue();
		//}
	}
	public static void main(String args[])
	{
		class_A ca = new class_A();
		SynchronizedMethod one = new SynchronizedMethod(ca);
		one.t.start();
		SynchronizedMethod two = new SynchronizedMethod(ca);
		two.t.start();
		SynchronizedMethod three = new SynchronizedMethod(ca);
		three.t.start();
	}
}
class class_A
{
	synchronized void printValue()
	{
		try
		{	int i;
			for(i=0;i<=5;i++)
			{
				System.out.print(i+" ");
				Thread.sleep(1000);
			}
			System.out.println("Thread Finish" );
		}
		catch(InterruptedException e)
		{
			System.out.println(e);
		}
	}
}

