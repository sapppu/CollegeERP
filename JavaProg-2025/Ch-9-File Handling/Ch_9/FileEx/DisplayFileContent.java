//Ex 1-  Program to display file contents in data.txt 

import java.io.*;
class DisplayFileContent
{
	public static void main(String args[])throws FileNotFoundException,IOException
	{
		FileInputStream fi=new FileInputStream("D:/JavaProg-2025/Ch_9/abc.txt");
		int n;
		char ch;
		while((n=fi.read())!=-1)
		{
			//System.out.print((char)n);
			ch=(char)n;
			System.out.print(Character.toUpperCase(ch));
		}
		fi.close();
	}
}
