 import java.io.*;
 class FileAppendCmdDemo
 {
 	public static void main(String args[]) throws FileNotFoundException,IOException 							                     IOException
 		{
			File f1= new File("D:/JavaExample/Ch_9/FileEx/abc.txt");
			File f2= new File("D:/JavaExample/Ch_9/FileEx/xyz.txt");
			if(f1.exists()&&f2.exists())
			{
				FileInputStream fin=new FilepInutStream(f2);
				FileOutputStream fout=new FileOutputStream(f1,true);
				int  n;
				while((n=fin.read())!= -1)
				{
					fout.write(n);
				}
				fin.close();
				fout.close();
			}
			else
			{ System.out.println("File doesn’t exist");  }
		}
}
