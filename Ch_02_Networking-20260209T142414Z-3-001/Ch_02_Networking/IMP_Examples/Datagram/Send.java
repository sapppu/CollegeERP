/* Send data */

import java.io.*;
import java.net.*;
import java.net.DatagramPacket;

class DatagramClient
{
	public static void main(String[ ] args) throws Exception 
	{
		try{
			InetAddress ia = InetAdress.getByName("127.0.0.1");		
			int port = Integer.parseInt("4444");
			DatagramSocket ds = new DatagramSocket();
			String str = "Hello......."; 
			byte buffer[ ] = str.getBytes();
			DatagramPacket dp = new DatagramPacket(buffer , Buffer.length);
			ds.send(dp);
		     }
		catch(Exception e)
		{
			System.out.println("Exception : " + e);
		}
}
