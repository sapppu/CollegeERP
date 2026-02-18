// To implement ECHO-SERVER

import java.io.*;
import java.net.*;

class EchoServer
{
	public static void main(String args[]) throws Exception
	{
		ServerSocket ss = new ServerSocket(4000);
		Socket s;

		while(true)
		{
			s = ss.accept();
			(new MyThread(s)).start();
		}
	}
}

class MyThread extends Thread
{
	DataInputStream  dis;
	DataOutputStream dos;
        String str;

	public MyThread(Socket s) throws Exception
	{
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
	}

	public void run()
	{
		try
		{
		   while(true)
                     
	           {	
                       str=dis.readUTF();
                       System.out.println("SERVER : " +str );
		       dos.writeUTF(str);
			
                   }
		}

		catch(Exception e)
		{
			System.out.println("EXCEPTION IN SERVER THREAD : " + e);
		}
		
	}	
 }