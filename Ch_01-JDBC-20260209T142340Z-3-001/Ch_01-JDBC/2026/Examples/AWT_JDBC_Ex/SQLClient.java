package AWT_JDBC_Ex;
import java.io.*;
import java.net.*;
//import java.sql.*;

public class SQLClient {
	
	public static void main(String args[])
	{
		Socket s;
		DataInputStream dis, din;
		DataOutputStream dos;
		try
		{
			s = new Socket(InetAddress.getLocalHost(),8080);

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
