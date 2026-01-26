import java.io.*;
class FiveTable extends Thread
{
	public void run()
	{
		for(int i=1;i<=5;i++)
		    System.out.println(i+"Fives are "+(i*5));
	}
}
class SevenTable extends Thread
{
	public void run()
	{
		for(int i=1;i<=5;i++)
		    System.out.println(i+"Sevens are "+(i*7));
	}
}
class ThirteenTable extends Thread
{
	public void run()
	{
		for(int i=1;i<=5;i++)
		    System.out.println(i+"Thirteens  are "+(i*13));
	}
}
class Sleep_isAliveDemo
{
	public static void main(String args[]) throws InterruptedException
	{
			FiveTable five=new FiveTable();
			SevenTable seven=new SevenTable();
	        		ThirteenTable thirteen=new ThirteenTable();
			five.start();
			seven.sleep(1000);
			
			if(seven.isAlive())
			{
				System.out.println("The Thread Seven Table is Alive");
			}
			else
			{
				System.out.println("The Thread Seven Table is Not Alive");
			}
			
			seven.join();
			seven.start();
			
			thirteen.join();
			thirteen.start();
	}
}
