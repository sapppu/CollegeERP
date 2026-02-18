import java.io.*;
import java.net.*;

class server7
	
{

	public static void main(String a[])throws Exception
	{
	
		try
		{
			Socket s;
			ServerSocket ss=new ServerSocket(8080);

				while(true)
					{
					s=ss.accept();
	
					(new myThread(s)).start();
					}
		}
	
		catch(Exception e)
		{
		}
	}
	
}

class myThread extends Thread 
	
{
  DataInputStream dis,din;
  DataOutputStream dos;

	 myThread(Socket s) throws Exception
	{
		
		dis=new DataInputStream(s.getInputStream());
		dos=new DataOutputStream(s.getOutputStream());
		din=new DataInputStream(System.in);
	}

	public void run()
	{
		
		try
		{
		System.out.println("Client:"+dis.readUTF());
		dos.writeUTF(din.readLine());
		}
		
		catch(Exception e)
		{
		}
	}
}

		
			