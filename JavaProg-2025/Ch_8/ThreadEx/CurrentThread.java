import java.io.*;
class CurrentThread
{
	public static void main (String args[])
	{
		Thread t=Thread.currentThread();           // Obtain the reference of main thread
		System.out.println("Current thread:"+t);
				// Change the name of the thread
		 t.setName("XYZ");
		 t.setPriority(7);
		 System.out.println ("After name change "+t);
		 try
		   {
		     for(int n=5;n<20;n++)
		      {
			System.out.println(n);
			t.sleep(500);
		      }
		   }
		catch ( InterruptedException e)
		    {
			System.out.println("Main thread is interrupted");
		    }
	}
}
