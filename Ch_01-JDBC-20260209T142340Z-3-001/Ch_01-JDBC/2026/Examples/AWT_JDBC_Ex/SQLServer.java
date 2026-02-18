package AWT_JDBC_Ex;
import java.net.*;
import java.io.*;
import java.sql.*;

public class SQLServer
{
	public static void main(String args[])
	{
		ServerSocket ss;
		Socket s;

		try
		{
			ss = new ServerSocket(8080);

			while(true)
			{
				s = ss.accept();
				(new MyThread(s)).start();
			}
		}

		catch(Exception e)
		{
			System.out.println("EXCEPTION ON SERVER SIDE : " + e);
		}
	}
}

class MyThread extends Thread
{
	Connection con;
	Statement st;
	ResultSet rs;

	DataInputStream din, dis;
	DataOutputStream dos;

	MyThread(Socket s) throws Exception
	{
		try
		{
			dis = new DataInputStream(s.getInputStream());
			//din = new DataInputStream(System.in);
			dos = new DataOutputStream(s.getOutputStream());

			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/socs","root","root123");
			st=con.createStatement();

		}

		catch(Exception e)
		{
			System.out.println("EXCEPTION ON SERVER SIDE (Thread Constructor) : " + e);
		}
	}

	public void run()
	{
		try
		{
			String str = dis.readUTF();
	
			rs = st.executeQuery(str);

			str = "";
			while(rs.next())
			{
				str = str + rs.getInt(1)+" \t "+rs.getString(2)+"\t "+rs.getInt(3)+"\n";  
			}
			dos.writeUTF(str);
		}		

		catch(Exception e)
		{
			System.out.println("EXCEPTION ON SERVER SIDE (Thread) : " + e);
		}
	}
}
