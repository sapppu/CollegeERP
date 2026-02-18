// To implement the Server which sends the requested file to client

import java.io.*;
import java.net.*;

class FileServer
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
	DataInputStream dis;
	DataOutputStream dos;
	FileInputStream fin;

	String fname;
	String data="";
		
	public MyThread(Socket s) throws Exception
	{
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
	}

	public void run()
	{
		try
		{
			fname = dis.readUTF();
			fin = new FileInputStream(fname);
		
			byte ch = (byte)fin.read();

			while (ch!=-1)
			{
				data = data + (char)ch;
				ch = (byte)fin.read();
			}

			dos.writeUTF(data);
		}

		catch(Exception e)
		{
		}
	}
}