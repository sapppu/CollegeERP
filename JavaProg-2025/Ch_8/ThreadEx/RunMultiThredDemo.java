class NewThread implements Runnable
{
	String name;   // name of threads
	NewThread(String  threadname)
	{
	 	name = threadname;
	    	Thread t=new Thread(this,name);
	    	System.out.println( "New Thread : "+t);
	    	t.start(); // Start The Thread
	}
	// This is an entry point for an Secondthread
		public void run()
		{     
		    try
		       {         for(int i=15;i>0;i--)
			   {
			     System.out.println(name +": " + i);
			     //Thread.sleep(200);
			    }
		        }
		catch(InterruptedException e)   
		        {
			     System.out.println(name + "Interrupted");	
		        }
		System.out.println(name + "exiting.");	         
	}
}
class RunMultiThredDemo
{
	public static void main(String args[])
	{
		//Thread t1= new Thread(this);
		NewThread n1= new NewThread("FirstThread ");	// Create threads.
		NewThread n2= new NewThread("SecondThread ");
		NewThread n3= new NewThread("LastThread ");
		try
	          	{
			// wait for other threads to end	
			//t1.sleep(10000);   // sleeping main Thread
			n1.t.join();
			n2.t.join();
			n3.t.join();
		  }
		catch(InterruptedException e)   
		        {
			System.out.println ("Main Thread Interrupted");	
		        }
			System.out.println ("Main Thread Exiting");
	}
}