
// Synchronize main thread uing join() and iAlive() method (without using sleep())

class NewThread implements Runnable
{
	String name;   // name of threads
	Thread t;
	NewThread(String threadname)
	{
	 	name = threadname;
	    	t=new Thread(this,name);  // Create Thread and assign the name for thread
	    	//t.setPriority(9);
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
			  		   System.out.println( name +": " + i);
			  		   Thread.sleep(1000);
			  	  }
	    	   }
	    	   catch( InterruptedException e)
	           	{
		     		System.out.println( name + "Interrupted");
	          	 }
		System.out.println("exit from :: " + name);

	}
}

class joinDemo
{
	public static void main( String args[])
	{
		NewThread n1=new NewThread("First");  // Using Constructor Create & Start new threads.
		NewThread n2=new NewThread("Second");
		NewThread n3=new NewThread("Last");

		System.out.println(" First Thread is alive : "+ n1.t.isAlive());
		System.out.println(" Second Thread is alive : "+ n2.t.isAlive());
		System.out.println(" Third Thread is alive : "+ n3.t.isAlive());
		try
	      	{
			System.out.println(" Waiting for the other threads to finish");
			n1.t.join();
			n2.t.join();
			n3.t.join();
		  }
		catch( InterruptedException e)
		  {
			System.out.println(" Main Thread Interrupted");
		  }

		System.out.println(" First Thread is alive : "+ n1.t.isAlive());
		System.out.println(" Second Thread is alive : "+ n2.t.isAlive());
		System.out.println(" Third Thread is alive : "+ n3.t.isAlive());

		System.out.println(" Main Thread exiting.");
	}
}


