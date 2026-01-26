
// Example :: Without using join() and iAlive method

class NewThread implements Runnable
{
	String name;   // name of threads
	Thread t;
	NewThread(String threadname)
	{
	 	name = threadname;
	    	t=new Thread(this,name);  // Create Thread and assign the name for thread
	    	System.out.println( "New Thread : "+t);
	    	t.start(); // Start The Thread
	}
	// This is an entry point for an Secondthread
	public void run()
	{
	       	   try
	    	   {
			  	for( int i=5;i>0;i--)
			  	  {
			  		   System.out.println( name+": " + i);
			  		   
			  	  }
			Thread.sleep(1000);
	    	   }
	    	   catch( InterruptedException e)
	           {
		     		System.out.println( name + "Interrupted");
	           }
		System.out.println( name + "exiting");

	}
}

class MultiThreadDemo
{
	public static void main( String args[])
	{
		try
	     	 {
		NewThread n1=new NewThread("First");  // Using Constructor Create & Start new threads.
		NewThread n2=new NewThread("Second");
		NewThread n3=new NewThread("Last");

		
			System.out.println(" Waiting for the other threads to finish");
			Thread.sleep(10000);
		  }
		catch(InterruptedException e1)
		  {
			System.out.println("Main Thread Interrupted"+e1);
		  }

		System.out.println(" Main Thread exiting.");
	}
}


