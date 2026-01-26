import java.io.*;
class PrintWriterDemo
{
	public static void main(String args[])
	{
	try
	{
		PrintWriter pw = new PrintWriter("F:/Ch_9/FileEx/abc.txt");
		pw.println(true);
		pw.println('A');
		pw.println(1500);
		pw.println(54.29);
		pw.println("Dr.C.Kulkarni");
		pw.close();
	}
	catch(Exception e)
	{
		System.out.println("Caught Exception::"+e);
	}
	}
}