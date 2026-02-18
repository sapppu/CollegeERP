// Server side

import java.net.*;
import java.io.*;

class sudp
{
	public static DatagramSocket ds;
	public static int client=789;
	public static int server=790;

	public static void main(String args[]) throws Exception
	{
			byte buffer[]=new byte[1024];
			ds = new DatagramSocket(server);
			
			DataInputStream dis= new DataInputStream(System.in); 
			System.out.println("Server waiting for input::");

			InetAddress inet= InetAddress.getByName("localhost");

			while(true)
			{
				String str=dis.readLine();
				buffer=str.getBytes();
				
				DatagramPacket dp = new DatagramPacket(buffer,str.length(),inet,client);
				
				ds.send(dp);
				if(str.equals("END"))
				{break;}
			}
	}
}
		
