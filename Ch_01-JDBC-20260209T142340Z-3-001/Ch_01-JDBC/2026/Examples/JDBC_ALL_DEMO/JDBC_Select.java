package JDBC_ALL_DEMO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_Select {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{  
			Class.forName("com.mysql.cj.jdbc.Driver");  // To loading MYsql JDBC Driver
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS12","root","root123");  
			// Establishing the Connection: here SOCS is database name, root is username and password is root123  
			Statement stmt=con.createStatement(); // To execute sql statement creating object of statement 
			ResultSet rs=stmt.executeQuery("select * from emp12");  // execute result  create object of Resul"com.mysql.cj.jdbc.Driver"set
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  

	}

}
