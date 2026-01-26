import java.io.*;
class FiveTable extends Thread
{
	public void run()
	{
		for(int i=1;i<=5;i++)
		    System.out.println(i +"Fives are "+ (i*5));
	}
}
class SevenTable extends Thread
{
	public void run()
	{
		for(int i=1;i<=5;i++)
		{
		    System.out.println(i +"Seven are "+ (i*7));
		}
	}
}
class ThirteenTable extends Thread
{
	public void run()
	{
		for(int i=1;i<=5;i++)
		{
		    System.out.println(i +"Thirteen are "+ (i*13));
		}
	}
}
class PriorityDemo
{
	public static void main(String args[]) throws IOException
	{
		FiveTable five = new FiveTable();
		SevenTable seven = new SevenTable();
		ThirteenTable thirteen = new ThirteenTable();

			five.setPriority(6);
			seven.setPriority(3);
			thirteen.setPriority(2);

			five.start();

			
			seven.start();
			seven.join();

			
			thirteen.start();
			thirteen.join();
	}
}
