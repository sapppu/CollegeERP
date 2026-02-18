/*To implement a CLIENT*/

import java.io.*;
import java.net.*;

class Client
{
	public static void main(String args[]) throws UnknownHostException,IOException
	{
		//try
		//{
			Socket s = new Socket(InetAddress.getLocalHost(), 8080);

			System.out.println("Receving no sended from Server");
			InputStream is = s.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			int n=dis.readInt();
			System.out.println("Received Random Number from Server is ::  " +n);
			dis.close();
			s.close();
		//}

		/*catch(Exception e)
		{
			System.out.println("EXCEPTION : " + e);
		}*/
	}
}