import java.io.*;
import java.net.*;

class client7
	
{

	public static void main(String a[])throws Exception
	{
		DataInputStream dis,din;
		DataOutputStream	dos;
		
		 try
		{
			Socket s=new Socket(InetAddress.getLocalHost(),8080);
			dis=new DataInputStream(s.getInputStream());
			din=new DataInputStream(System.in);
			dos=new DataOutputStream(s.getOutputStream());
		
		while(true)
		{
		dos.writeUTF(din.readLine());
		System.out.println("Client:"+dis.readUTF());
		}

		}
		catch(Exception e)
		{
		}

	}
}	