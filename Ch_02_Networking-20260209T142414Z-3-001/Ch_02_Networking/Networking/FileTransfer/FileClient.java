/* Client to Send the File Name to be retrieved */

import java.io.*;
import java.net.*;

class FileClient
{
	public static void main(String args[])
	{
		Socket s;
		DataInputStream dis, din;
		DataOutputStream dos;

		try
		{
			s = new Socket (InetAddress.getLocalHost(), 4000);

			din = new DataInputStream(System.in);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());

			while(true)
			{
				dos.writeUTF(din.readLine());
				System.out.println(dis.readUTF());
			}
		}

		catch(Exception e)
		{
			System.out.println("EXCEPTION IN CLIENT : " + e);
		}
	}
}