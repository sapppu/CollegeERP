/*WAP to generate random numbers on server side and send it to client */

import java.io.*;
import java.net.*;
import java.util.*;
class Server
{
	public static void main(String args[]) throws UnknownHostException,IOException
	{
			ServerSocket ss = new ServerSocket(8080);
			System.out.println("Server Started");
			Random r = new Random();
			while(true) 		                                   // infinite loop
			{
				Socket s = ss.accept();
				System.out.println("Sending number to client ");
				OutputStream os= s.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeInt(r.nextInt());                                            // generate random nos.
				System.out.println("Number send to client");;
				dos.close();
				s.close();
			}
		
	}
}

