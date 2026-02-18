// To implement the server
	import java.io.*;
	import java.net.*;
	class Server
	{
		public static void main(String args[])
		{
			try
			{
				Socket s;
				ServerSocket ss=new ServerSocket(8080);
				while(true)
				{
					s=ss.accept();
					(new MyThread(s)).start();
				}
			}

			catch(Exception e)
			{
				System.out.println("EXCEPTION:"+e);
			}
		}
	}

	class MyThread extends Thread
	{
		 DataInputStream dis,din;
		 DataOutputStream dos;

		MyThread(Socket s) throws Exception
		{
			dis=new DataInputStream(s.getInputStream());
			din=new DataInputStream(System.in);
			dos=new DataOutputStream(s.getOutputStream());
		}

			public void run()
			{
				try
				{
					while(true)
					{
					String text=dis.readUTF();
					System.out.println("CLIENT:"+text);
					dos.writeUTF(text);
					}

				}
	
				catch(Exception e)
				{
					System.out.println("EXCEPTION:"+ e);
				}
			}
		
	}