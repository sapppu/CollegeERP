/*To implement a SERVER*/

import java.io.*;
import java.net.*;

class Server
{
	public static void main(String args[])
	{
		try
		{
			Socket s;
			ServerSocket ss = new ServerSocket(8080);

			while(true)
			{
				s = ss.accept();
				(new MyThread(s)).start();
			}
		}

		catch(Exception e)
		{
			System.out.println("EXCEPTION : " + e);
		}
	}
}

class MyThread extends Thread
{
	DataInputStream din, dis;
	DataOutputStream dos;

	MyThread(Socket s) throws Exception
	{
		dis = new DataInputStream(s.getInputStream());
		din = new DataInputStream(System.in);
		dos = new DataOutputStream(s.getOutputStream());
	}

	public void run()
	{
		
                 
                try
		{
                      while(true)
                      {

			System.out.println("CLIENT : " + dis.readUTF());
			dos.writeUTF(din.readLine());

                     }
		}

		catch(Exception e)
		{
			System.out.println("EXECPTION IN SERVER :" + e);
		}
	}
}