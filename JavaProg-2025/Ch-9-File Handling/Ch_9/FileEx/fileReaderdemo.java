import java.io.*;
class fileReaderdemo
{
	public static void main(String args[])throws FileNotFoundException,IOException
	{
		try
		{
		   FileReader fr = new FileReader("D:/JavaProg-2025/Ch_9/FileEx/abc.txt");
		   int i;
		   while((i=fr.read())!=-1)
		   {
			System.out.print((char)i);
		   }
		   fr.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception : "+e);
		}

	}
}
