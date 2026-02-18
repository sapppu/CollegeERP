// To implemet the Echo-Client

import java.io.*;
import java.net.*;

class EchoClient
{
	static Socket s;
	static DataInputStream dis, din;
	static DataOutputStream dos;

	public static void main(String args[])
	{
		try
		{
			s = new Socket(InetAddress.getLocalHost(), 4000);
			din = new DataInputStream(System.in);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());

			while(true)
			{
				dos.writeUTF(din.readLine());
				System.out.println("SERVER : " + dis.readUTF());
			}
		}

		catch(Exception e)
		{
			System.out.println("EXCEPTION IN CLIENT : " + e);
		}
	}
}