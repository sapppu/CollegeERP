/*To implement a SERVER*/

import java.io.*;
import java.net.*;

class Server
{
	public static void main(String args[]) throws UnknownHostException,IOException
	{
		//try
		//{
			ServerSocket ss = new ServerSocket(8080);
			System.out.println("Server Started");
			Socket s = ss.accept();
			System.out.println("Client is connected");

			DataInputStream dis = new DataInputStream(System.in);
			System.out.println("Enter the Message to send for Client ::");
			String msg= dis.readLine();

			OutputStream os= s.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF(msg);

			System.out.println("Message send to client");;
			dis.close();
			dos.close();
			s.close();
		//}

		/*catch(Exception e)
		{
			System.out.println("EXCEPTION : " + e);
		}*/
	}
}

