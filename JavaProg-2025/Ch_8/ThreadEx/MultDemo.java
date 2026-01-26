class Demo1 implements Runnable
{
	Demo1()
	{
		Thread t1=new Thread(this,"MyThread");
		System.out.println("Are u in childthread Starting mode::"+t1);
		t1.start();
	}
	public void run()
	{
		try
		{
			for(int i=10;i>0;i--)
			{
				System.out.println("value of i=="+i);
				Thread.sleep(1000);
			}
			System.out.println("complition of child thread");
		}
		catch(InterruptedException e)
		{
			System.out.println("child Thread intrupting:::::"+e);
		}
	}
}
class MultDemo
{
	public static void main(String[] args) 
	{
		new Demo1();
		try
		{
			for(int j=10;j>0;j--)
			{
				System.out.println("value of j=="+j);
				Thread.sleep(500);
			}
			System.out.println("complition of main thread");
		}
		catch(InterruptedException e)
		{
			System.out.println("Main thred intrrupting::::");
		}
	}
}
