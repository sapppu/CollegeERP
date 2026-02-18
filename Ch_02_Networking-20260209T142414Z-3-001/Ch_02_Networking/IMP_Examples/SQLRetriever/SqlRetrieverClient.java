import java.io.*;
import java.net.*;
import java.sql.*;

class SqlRetrieverClient
{
	public static void main(String args[])
	{
		Socket s;
		DataInputStream dis, din;
		DataOutputStream dos;
		try
		{
			s = new Socket(InetAddress.getLocalHost(),8000);
			System.out.println("Enter the querry:");

			din = new DataInputStream(System.in);

			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());

			String str = din.readUTF();
			dos.writeUTF(str);
			System.out.println("" + dis.readUTF());
		}

		catch(Exception e)
		{
			System.out.println("Exception on Client Side : " + e);
		}
	}
}