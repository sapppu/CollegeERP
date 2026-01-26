class NewThread implements Runnable
{
	String name;
	Thread t;
	NewThread()
	{
		// Create a new, Second Thread
		t=new Thread( this,"demoRunnableThread ");   //   this indicate you want to create new thread to call the run() on this object
		System.out.println("Child Thread : "+t);
		t.start();
	}

	// This is an entry point for an Secondthread
	public void run()
	{
	   	try
	     	{
			  for(int i=5;i>0;i--)
			   {
				     System.out.println("Child Thread :"+i);
					Thread.sleep(500);
			   }
		}
		catch( InterruptedException e)
		{
			System.out.println( "Child Thread Interrupted");
		}

		System.out.println("Exiting Child Thread");
	}

}
class RunInterfaceDemo
{
	public static void main(String[] args)
	{
		new NewThread();     // Create a new thread.
		try
		{
			for(int i=25;i>0;i--)
			{	Thread.sleep(1000);
				System.out.println("Main Thread:"+i);
				
			}
        		}
		catch(InterruptedException e)
		{
			System.out.println("Main Thread Intrrupted");
		}

		System.out.println("Main Thread Exiting");

	}
}
