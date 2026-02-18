/* receiver */

import java.net.*;
import java.io.*;

class DatagramServer
{
	//public static DatagramSocket ds;
	public static void main(String args[])
	{
		try
		{
			
			DatagramSocket ds = new DatagramSocket(2000);		
			byte buffer[] = new byte[20];
			while( true )
			{
				DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
				ds.receive(dp);
				String str = new String (dp.getData( ));
				System.out.println("Data from Client is " + str);
			}
		}
		catch(Exception e)
		{
			//System.out.println(e);
			e.printStackTrace();
		}
	}
}
