import java.io.*;
class filecreate
{
 	public static void main(String args[]) 	                    
 		{
			try{
				String s="Welcome";
				byte b[]= s.getBytes();
				FileOutputStream fout=new FileOutputStream("D:/JavaProg-2025/Ch_9/abc.txt");
				fout.write(b);
				System.out.println("File is created");
				fout.close();
			}
			catch(FileNotFoundException e1)
			{
				System.out.println("File Error"+e1);
			}
			catch(IOException e2)
			{
				System.out.println("Error"+e2);
			}

		}
  }
