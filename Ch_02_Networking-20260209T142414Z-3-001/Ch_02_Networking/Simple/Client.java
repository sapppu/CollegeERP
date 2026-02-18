/*To implement a CLIENT*/

import java.io.*;
import java.net.*;

class Client
{
	static DataInputStream din, dis;
	static DataOutputStream dos;

	public static void main(String args[])
	{
		try
		{
			Socket s = new Socket(InetAddress.getLocalHost(), 8080);

			din = new DataInputStream(s.getInputStream());
			dis = new DataInputStream(System.in);

			dos = new DataOutputStream(s.getOutputStream());

			while(true)
			{
				dos.writeUTF(dis.readLine());
				System.out.println("SERVER : " + din.readUTF());
			}
		}

		catch(Exception e)
		{
			System.out.println("EXCEPTION : " + e);
		}
	}
}