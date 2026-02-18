package JDBC_ALL_DEMO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Update

{
	
	// TODO Auto-generated method stub
	
	public static void main(String[] args) throws SQLException 
	{
			Connection con;
			Statement stmt = null;
								
				try
				{  
					Class.forName("com.mysql.cj.jdbc.Driver");  // To loading MYsql JDBC Driver
				
				}
				catch (java.lang.ClassNotFoundException e1)
				{
					System.out.println(e1.getMessage());
				}
			
				try
				{
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/SOCS12","root","root123");  
					
					stmt=con.createStatement(); 
					
					ResultSet r1 = stmt.executeQuery("Select TEA_NAME,PRICE from teatable");					
								
					while(r1.next())
	    			 {
	    				 String s= r1.getString("TEA_NAME");
	    				 float f= r1.getFloat("PRICE");
	    				 System.out.println(s +"  "+ f);
	    			 }
				
				}	
				catch(SQLException ex)
				{ 
					System.out.println("Sql Exeception Error"+ ex.getMessage());
				} 
				
				try {
					stmt.executeUpdate("UPDATE teatable SET PRICE=334 WHERE TEA_NAME='ThreeRoseTea'");
				}
				catch(Exception e)
				{ 
					System.out.println(e.getMessage());
				} 
				
				ResultSet rs = stmt.executeQuery("Select TEA_NAME,PRICE from teatable");
				
				while(rs.next())
				{
					String s1= rs.getString("TEA_NAME"); 
					float p= rs.getFloat("PRICE"); 
					System.out.println(s1+"  "+p);
					
				}
				
		}	
				

	}





