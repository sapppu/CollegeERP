//Ex 1-  Program to illustrate the use of Filewriter class  

import java.io.*;
class filewriterdemo
{
	public static void main(String args[])throws FileNotFoundException,IOException
	{
		try
		{
		   FileWriter fw = new FileWriter("D:/JavaProg-2025/Ch_9/abc.txt");
		   for(char i=65;i<91;i++)
		   {
			fw.write(i);
		   }
		   fw.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception : "+e);
		}

	}
}
