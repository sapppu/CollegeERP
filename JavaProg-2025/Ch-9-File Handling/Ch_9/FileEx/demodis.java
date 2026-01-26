//Ex 1- To Accept no. form user.

 import java.io.*;
 class demodis
 {
 	public static void main(String args[]) 
 		{
			try
			{	
				DataInputStream dis=new DataInputStream(System.in);
				System.out.println("Enter First No:");
				int a=Integer.parseInt(dis.readLine());
				System.out.println("Entered no.is " + a);
			} catch(IOException e)
			{
				 System.out.println("Error");
			}
		}
  }
	
