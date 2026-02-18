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
			InputStream is = s.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			String s1 = dis.readUTF();
			System.out.println("Received String is ::  " +s1);
			dis.close();
			s.close();
		//}

		/*catch(Exception e)
		{
			System.out.println("EXCEPTION : " + e);
		}*/
	}
}