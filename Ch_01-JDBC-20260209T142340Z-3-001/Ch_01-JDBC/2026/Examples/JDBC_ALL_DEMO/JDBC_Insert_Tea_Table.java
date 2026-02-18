package JDBC_ALL_DEMO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Insert_Tea_Table 

{
	
	// TODO Auto-generated method stub
	
	public static void main(String[] args) 
	{
			Connection con;
			Statement stmt;
								
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
					
					
					stmt.executeUpdate("Insert into teatable values('Nilgiri',201,17.99,0,0)");  // execute result  create object of Resulset
					stmt.executeUpdate("Insert into teatable values('GoldenTea',14,18.99,0,0)");
					stmt.executeUpdate("Insert into teatable values('BreezeTea',250,19.99,0,0)");
					stmt.executeUpdate("Insert into teatable values('ThreeRoseTea',201,18.99,0,0)");
					stmt.executeUpdate("Insert into teatable values('SpecialTea',149,19.99,0,0)");
					
					ResultSet rs = stmt.executeQuery("Select * from teatable");					
					
					System.out.println("TeaName and Prices are :");
					
					while(rs.next())
	    			 {
	    				 String s= rs.getString("TEA_NAME");
	    				 float f= rs.getFloat("PRICE");
	    				 System.out.println(s +"  "+ f);
	    			 }
					stmt.close();
					con.close();
				}	
				
				catch(SQLException ex)
				{ 
					System.out.println("Sql Exeception Error"+ ex.getMessage());
				} 

	}
}
