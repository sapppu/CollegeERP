class nthread implements Runnable 
{
	String name;
	Thread t;
	nthread()
	{
		name=OrignalThread;
		Thread t=new Thread(this,name);
		System.out.println("New Thread"+t);
		t.start();
	}
	public void run()
	{
		try{
		for(int n=10;n>0;i--)
		{
			System.out.println("name"+"."+n);
			Thread.sleep();
		}
		}
		catch(InterruptedException e)
		{
			System.out.println(name+"Thread is Interrupted::");
		}
	System.out.println("complition of main thread");
	}
}
class MultiThreadDemo
{
};
{
	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
	}
}
