class A extends Thread
	{
		public void run()
		{
		     for(int i=0;i<=50;i++)
			{
			    System.out.println("\t From Thread A : i = "+i);
			}
			System.out.println("Exit from A");
		}
	}
class B extends Thread
{
	public void run()
	{
	     for(int j=0;j<=50;j++)
		{
		    System.out.println("\t From Thread B : j = "+j);
		}
		    System.out.println("Exit from B");
	}
}
class C extends Thread
{
	public void run()
	{
	     for(int k=0;k<=50;k++)
		{
		   System.out.println("\t From Thread C : k = "+k);
		}
		System.out.println("Exit from C");
	}
}
class ThreadTest
{
        public static void main(String args[])
	{
		A  threadA = new A();
		
		
		B  threadB = new B();
		
		
		C  threadC = new C();
		
	
		threadA.setPriority(6);
		threadB.setPriority(2);
		threadC.setPriority(9);

		threadA.start();
		threadB.start();
		threadC.start();

		
		
		
		//threadB.sleep(1000);
		
		//threadC.sleep(1000);


	}
}
