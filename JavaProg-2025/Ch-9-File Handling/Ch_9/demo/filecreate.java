import java.io.*;
 class filecreate
 {
 	public static void main(String args[]) throws FileNotFoundException,IOException
 		{
		      String s="Welcome";
		      byte b[]= s.getBytes();
		      FileOutputStream fout=new FileOutputStream("D:/JavaProg-2025/Ch_9/demo/abc.txt");
		      fout.write(b);
		      System.out.println("File is created");
		      fout.close();		
		}
  }
