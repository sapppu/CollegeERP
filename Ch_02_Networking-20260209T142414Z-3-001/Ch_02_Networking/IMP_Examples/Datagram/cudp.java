/* Client Side */

import java.io.*;
import java.net.*;
class cudp
{
	public static DatagramSocket ds;
	public static int client=789;
	public static int server=790;

	public static void main(String[ ] args) throws Exception
	{
			byte buffer[]=new byte[1024];
			ds = new DatagramSocket(client);

			System.out.println("Client waiting for server::");
			while(true)
			{
				DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
				ds.receive(dp);
	
				String str = new String(dp.getData(),0,dp.getLength());

				if(str.equals("END"))
					{ break;}
				else
					{ System.out.println(str);}
			}

				
		
	}
}
