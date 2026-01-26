import java.io.*;
class file1
{
	public static void main(String args[])throws FileNotFoundException,IOException
	{
		FileInputStream fi=new FileInputStream("E:\\JavaExample\\abc.txt");
		int n;
		while((n=fi.read())!=-1)
		{
			System.out.print((char)n);
		}
		fi.close();
	}
}
