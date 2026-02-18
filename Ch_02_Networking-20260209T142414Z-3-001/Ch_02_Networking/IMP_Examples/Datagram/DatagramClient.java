/* Send data */

import java.io.*;
import java.net.*;
class DatagramClient
{
	public static void main(String[ ] args)
	{
		try{
			InetAddress ia = InetAddress.getByName("172.16.64.221");		
			int port = Integer.parseInt("2000");
			DatagramSocket ds = new DatagramSocket();
			String str1 = "Hello......."; 
			byte buffer[ ] = new byte[1024]; 
			buffer = str1.getBytes();
			DatagramPacket dp = new DatagramPacket(buffer,buffer.length,ia,2000);
			ds.send(dp);
		     }
		catch(Exception e)
		{
			System.out.println("Exception : " + e);
		}
	}
}
